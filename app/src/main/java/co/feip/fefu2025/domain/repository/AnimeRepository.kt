package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.models.Anime

interface AnimeRepository {
    suspend fun getAnimeList(page: Int): List<Anime>
    suspend fun getAnimeById(id: Int): Anime
    suspend fun getGlobalRecommendations(currentAnimeId: Int): List<Anime>
    suspend fun searchAnime(query: String): List<Anime>
}