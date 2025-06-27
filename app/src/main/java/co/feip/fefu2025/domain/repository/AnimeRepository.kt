package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.domain.models.Anime

interface AnimeRepository {
    suspend fun getAnimeList(): List<Anime>
    suspend fun getAnimeById(id: Int): Anime
}