package co.feip.fefu2025.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import kotlinx.coroutines.launch

class AnimeListViewModel(private val getAnimeListUseCase: GetAnimeListUseCase) : ViewModel() {
    val animeList: SnapshotStateList<Anime> = mutableStateListOf()
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    init {
        loadAnimeList()
    }

    fun loadAnimeList() {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                val list = getAnimeListUseCase()
                animeList.clear()
                animeList.addAll(list)
            } catch (e: Exception) {
                error.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }

    class Factory(private val useCase: GetAnimeListUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AnimeListViewModel(useCase) as T
        }
    }
}