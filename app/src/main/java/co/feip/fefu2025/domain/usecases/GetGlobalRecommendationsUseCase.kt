package co.feip.fefu2025.domain.usecases

import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.repository.AnimeRepository


class GetGlobalRecommendationsUseCase(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(currentAnimeId: Int): List<Anime> {
        return repository.getGlobalRecommendations(currentAnimeId)
    }
}