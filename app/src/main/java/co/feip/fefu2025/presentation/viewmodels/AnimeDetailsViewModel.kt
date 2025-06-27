package co.feip.fefu2025.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeDetailsUseCase
import kotlinx.coroutines.launch

class AnimeDetailsViewModel(
    private val getAnimeDetailUseCase: GetAnimeDetailsUseCase,
    private val animeId: Int
) : ViewModel() {

    val anime = mutableStateOf<Anime?>(null)

    init {
        loadAnime()
    }

    private fun loadAnime() {
        viewModelScope.launch {
            anime.value = getAnimeDetailUseCase(animeId)
        }
    }

    class Factory(
        private val getAnimeDetailUseCase: GetAnimeDetailsUseCase,
        private val animeId: Int
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AnimeDetailsViewModel(getAnimeDetailUseCase, animeId) as T
        }
    }
}