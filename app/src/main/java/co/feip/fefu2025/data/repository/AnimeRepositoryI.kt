package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.remote.RetrofitClient
import co.feip.fefu2025.data.remote.dto.extensions.toDomain
import co.feip.fefu2025.data.remote.dto.extensions.toRatings
import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.repository.AnimeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout

class AnimeRepositoryI : AnimeRepository {
    private val apiService = RetrofitClient.apiService

    private val baseDelay = 1000L
    private val maxRecommendations = 15
    private val maxSearchResults = 20

    override suspend fun getAnimeList(page: Int): List<Anime> {
        return try {
            apiService.getTopAnime(page).data
                .mapNotNull { dto ->
                    dto.takeIf { it.title?.isNotEmpty() == true }?.toDomain()
                }.also {
                    if (it.isEmpty() && page == 1) throw Exception("Список аниме пуст")
                }
        } catch (e: Exception) {
            throw Exception("Ошибка загрузки страницы $page: ${e.message}")
        }
    }

    override suspend fun getAnimeById(id: Int): Anime {
        return try {
            val details = apiService.getAnimeDetails(id).data.toDomain()

            val ratings = try {
                apiService.getStatistics(id).data.toRatings()
            } catch (e: Exception) {
                emptyList()
            }

            val recommendations = loadRecommendations(id, 5)

            details.copy(
                ratings = ratings,
                recommendations = recommendations,
                recommendationIds = recommendations.map { it.id }
            )
        } catch (e: Exception) {
            throw Exception("Ошибка загрузки аниме: ${e.message}")
        }
    }

    override suspend fun getGlobalRecommendations(currentAnimeId: Int): List<Anime> {
        return try {
            val initialList = apiService.getTopAnime().data
                .map { it.toDomain() }
                .filter { it.id != currentAnimeId }
                .take(25)

            ensureFullRecommendations(initialList, maxRecommendations)
        } catch (e: Exception) {
            throw Exception("Ошибка загрузки рекомендаций: ${e.message}")
        }
    }

    override suspend fun searchAnime(query: String): List<Anime> {
        return try {
            val rawResults = apiService.searchAnime(query).data
                .map { it.toDomain() }
                .take(maxSearchResults * 2)

            ensureFullRecommendations(rawResults, maxSearchResults)
        } catch (e: Exception) {
            throw Exception("Ошибка поиска: ${e.message}")
        }
    }

    private suspend fun loadRecommendations(animeId: Int, count: Int): List<Anime> {
        return try {
            val recIds = apiService.getRecommendations(animeId).data
                .mapNotNull { it.entry?.id }
                .distinct()
                .take(count * 2)

            recIds.mapNotNull { id ->
                try {
                    delay(baseDelay)
                    val anime = apiService.getAnimeDetails(id).data.toDomain()
                    if (isValidFullAnime(anime)) anime else null
                } catch (e: Exception) {
                    null
                }
            }.take(count)
        } catch (e: Exception) {
            emptyList()
        }
    }

    private suspend fun ensureFullRecommendations(
        initialList: List<Anime>,
        targetCount: Int
    ): List<Anime> {
        val valid = initialList.filter { isValidFullAnime(it) }

        return if (valid.size >= targetCount) {
            valid.take(targetCount)
        } else {
            val additional = loadAdditionalAnime(
                excludeIds = initialList.map { it.id },
                count = targetCount - valid.size
            )
            (valid + additional).take(targetCount)
        }
    }

    private suspend fun loadAdditionalAnime(excludeIds: List<Int>, count: Int): List<Anime> {
        return try {
            val additionalIds = apiService.getTopAnime().data
                .map { it.id }
                .filterNot { excludeIds.contains(it) }
                .take(count * 2)

            additionalIds.mapNotNull { id ->
                try {
                    delay(baseDelay)
                    val anime = apiService.getAnimeDetails(id).data.toDomain()
                    if (isValidFullAnime(anime)) anime else null
                } catch (e: Exception) {
                    null
                }
            }.take(count)
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun isValidFullAnime(anime: Anime): Boolean {
        return anime.title.isNotEmpty() &&
                anime.imageUrl?.isNotEmpty() == true &&
                anime.genres.isNotEmpty() &&
                anime.rating.isNotEmpty() &&
                anime.year?.isNotEmpty() == true &&
                anime.season?.isNotEmpty() == true
    }
}