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
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)
    val recommendationsLoading = mutableStateOf(false)

    init {
        loadAnimeData()
    }

    fun loadAnimeData() {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                anime.value = getAnimeDetailUseCase(animeId)
            } catch (e: Exception) {
                error.value = "Ошибка загрузки данных: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun loadRecommendations() {
        if (anime.value?.recommendations.isNullOrEmpty()) {
            viewModelScope.launch {
                recommendationsLoading.value = true
                try {
                    val current = anime.value ?: return@launch
                    val updated = current.copy(
                        recommendations = getAnimeDetailUseCase(current.id).recommendations
                    )
                    anime.value = updated
                } catch (e: Exception) {
                    error.value = "Ошибка загрузки рекомендаций: ${e.message}"
                } finally {
                    recommendationsLoading.value = false
                }
            }
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