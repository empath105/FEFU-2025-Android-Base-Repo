package co.feip.fefu2025.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.usecases.GetSearchUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

class SearchViewModel(
    private val searchAnimeUseCase: GetSearchUseCase
) : ViewModel() {
    val searchQuery = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    val animeList = mutableStateOf<List<Anime>>(emptyList())
    val error = mutableStateOf<String?>(null)

    private var searchJob: Job? = null


    fun search(query: String) {
        searchQuery.value = query
        searchJob?.cancel()

        if (query.isBlank()) {
            animeList.value = emptyList()
            error.value = null
            return
        }
        isLoading.value = true
        error.value = null
        searchJob = viewModelScope.launch {
            try {
                delay(300)
                isLoading.value = true
                error.value = null
                val results = searchAnimeUseCase(query)
                animeList.value = results
            } catch (e: CancellationException) {

            } catch (e: Exception) {
                error.value = e.message ?: "Ошибка поиска"
                animeList.value = emptyList()
            } finally {
                isLoading.value = false
            }
        }
    }

    class Factory(
        private val useCase: GetSearchUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(useCase) as T
        }
    }
}