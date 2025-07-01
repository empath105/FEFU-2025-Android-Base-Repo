package co.feip.fefu2025.domain.usecases

import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.repository.AnimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSearchUseCase(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(query: String): List<Anime> = withContext(Dispatchers.IO) {
        repository.searchAnime(query)
    }
}