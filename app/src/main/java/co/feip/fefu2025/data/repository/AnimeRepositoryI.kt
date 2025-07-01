package co.feip.fefu2025.data.repository

import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.repository.AnimeRepository
import co.feip.fefu2025.data.AnimeData
import kotlinx.coroutines.delay
import kotlin.random.Random

class AnimeRepositoryI : AnimeRepository {
    private val allAnime = AnimeData.getAnimeList()
    private val globalRecommendationIds = listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    override suspend fun getAnimeList(): List<Anime> {
        delay(1000)
        if (Random.nextBoolean()) throw Exception("Ошибка загрузки списка аниме")
        return allAnime
    }

    override suspend fun getAnimeById(id: Int): Anime {
        delay(1500)
        if (Random.nextBoolean()) throw Exception("Ошибка загрузки данных аниме")

        val anime = allAnime.first { it.id == id }
        val recommendations = anime.recommendationIds
            ?.mapNotNull { rid -> allAnime.find { it.id == rid } }
            ?.take(3) ?: emptyList()
        return anime.copy(recommendations = recommendations)
    }

    override suspend fun getGlobalRecommendations(currentAnimeId: Int): List<Anime> {
        delay(1000)
        if (Random.nextBoolean()) throw Exception("Ошибка загрузки рекомендаций")

        return globalRecommendationIds
            .mapNotNull { id -> allAnime.find { it.id == id } }
            .filter { it.id != currentAnimeId }
            .take(10)
    }

    override suspend fun searchAnime(query: String): List<Anime> {
        delay(500)
        if (Random.nextBoolean()) throw Exception("Ошибка поиска")

        return if (query.isBlank()) {
            emptyList()
        } else {
            allAnime.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.genres.any { genre -> genre.contains(query, ignoreCase = true) }
            }
        }
    }
}
