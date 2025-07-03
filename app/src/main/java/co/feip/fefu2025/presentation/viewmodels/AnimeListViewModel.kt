package co.feip.fefu2025.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {
    private var currentPage = 1
    var hasNextPage = true

    val animeList = mutableStateListOf<Anime>()
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)


    init {
        loadInitialData()
    }

    fun loadInitialData() {
        if (animeList.isEmpty()) {
            loadMoreData()
        }
    }

    fun loadMoreData(reset: Boolean = false) {
        if (isLoading.value) return

        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                if (reset) {
                    currentPage = 1
                    animeList.clear()
                }
                val newItems = getAnimeListUseCase(currentPage).distinctBy { it.id }
                animeList.addAll(newItems)
                hasNextPage = newItems.isNotEmpty()
                if (newItems.isNotEmpty()) currentPage++
            } catch (e: Exception) {
                error.value = " ${e.message}"

            } finally {
                isLoading.value = false
            }
        }
    }

    fun retryLoading() {
        loadMoreData()
    }

    class Factory(
        private val useCase: GetAnimeListUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AnimeListViewModel(useCase) as T
        }
    }
}