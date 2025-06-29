package co.feip.fefu2025.data.repository

import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.repository.AnimeRepository
import co.feip.fefu2025.data.AnimeData

class AnimeRepositoryI : AnimeRepository {
    private val allAnime = AnimeData.getAnimeList()
    private val globalRecommendationIds = listOf(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    override suspend fun getAnimeList(): List<Anime> = allAnime

    override suspend fun getAnimeById(id: Int): Anime {
        val anime = allAnime.first { it.id == id }
        val recommendations = anime.recommendationIds
            ?.mapNotNull { rid -> allAnime.find { it.id == rid } }
            ?.take(3) ?: emptyList()
        return anime.copy(recommendations = recommendations)
    }

    override suspend fun getGlobalRecommendations(currentAnimeId: Int): List<Anime> {
        return globalRecommendationIds
            .mapNotNull { id -> allAnime.find { it.id == id } }
            .filter { it.id != currentAnimeId }
            .take(10)
    }
}
