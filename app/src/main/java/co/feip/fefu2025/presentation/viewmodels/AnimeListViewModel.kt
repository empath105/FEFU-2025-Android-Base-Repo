package co.feip.fefu2025.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.domain.models.Anime
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import kotlinx.coroutines.launch

class AnimeListViewModel(private val getAnimeListUseCase: GetAnimeListUseCase) : ViewModel() {
    val animeList: SnapshotStateList<Anime> = mutableStateListOf()

    init {
        loadAnimeList()
    }

    private fun loadAnimeList() {
        viewModelScope.launch {
            try {
                val list = getAnimeListUseCase()
                animeList.clear()
                animeList.addAll(list)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    class Factory(private val useCase: GetAnimeListUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AnimeListViewModel(useCase) as T
        }
    }
}