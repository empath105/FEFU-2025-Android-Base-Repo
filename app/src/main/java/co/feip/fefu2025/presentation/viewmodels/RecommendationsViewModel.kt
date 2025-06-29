package co.feip.fefu2025.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.usecases.GetGlobalRecommendationsUseCase
import kotlinx.coroutines.launch

class RecommendationsViewModel(
    private val getGlobalRecommendationsUseCase: GetGlobalRecommendationsUseCase,
    private val excludeAnimeId: Int? = null
) : ViewModel() {
    val recomendList = mutableStateOf<List<Anime>>(emptyList())
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    init {
        loadRecommendations()
    }

    fun loadRecommendations() {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                val recommendations = if (excludeAnimeId != null) {
                    getGlobalRecommendationsUseCase.invoke(excludeAnimeId)
                } else {
                    getGlobalRecommendationsUseCase.invoke(0)
                }
                recomendList.value = recommendations
            } catch (e: Exception) {
                error.value = e.message
                recomendList.value = emptyList()
            } finally {
                isLoading.value = false
            }
        }
    }

    class Factory(
        private val useCase: GetGlobalRecommendationsUseCase,
        private val excludeAnimeId: Int? = null
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RecommendationsViewModel(useCase, excludeAnimeId) as T
        }
    }
}