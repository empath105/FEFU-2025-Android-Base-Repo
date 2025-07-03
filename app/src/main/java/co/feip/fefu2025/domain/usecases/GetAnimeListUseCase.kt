package co.feip.fefu2025.domain.usecases

import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.repository.AnimeRepository

class GetAnimeListUseCase(private val repository: AnimeRepository) {
    suspend operator fun invoke(page: Int): List<Anime> {
        return repository.getAnimeList(page)
    }
}

