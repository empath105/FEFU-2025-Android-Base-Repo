package co.feip.fefu2025.data.repository

import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.repository.AnimeRepository
import co.feip.fefu2025.data.AnimeData

class AnimeRepositoryI : AnimeRepository {
    private val allAnime = AnimeData.getAnimeList()

    override suspend fun getAnimeList(): List<Anime> = allAnime

    override suspend fun getAnimeById(id: Int): Anime {
        return allAnime.first { it.id == id }
    }
}

