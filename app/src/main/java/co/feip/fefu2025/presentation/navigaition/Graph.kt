package co.feip.fefu2025.presentation.navigaition

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import co.feip.fefu2025.MainAnimeScreen
import co.feip.fefu2025.AnimeCardInfo
import co.feip.fefu2025.data.repository.AnimeRepositoryI
import co.feip.fefu2025.domain.usecases.GetAnimeDetailsUseCase
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import co.feip.fefu2025.presentation.viewmodels.AnimeListViewModel
import co.feip.fefu2025.presentation.viewmodels.AnimeDetailsViewModel

@Composable
fun Graph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val repository = AnimeRepositoryI()
    val listUseCase = GetAnimeListUseCase(repository)
    val detailUseCase = GetAnimeDetailsUseCase(repository)

    val mainViewModelFactory = AnimeListViewModel.Factory(listUseCase)

    NavHost(
        navController = navController,
        startDestination = "main",
        modifier = modifier
    ) {
        composable("main") {
            val mainViewModel: AnimeListViewModel = viewModel(factory = mainViewModelFactory)
            MainAnimeScreen(
                viewModel = mainViewModel,
                onAnimeClick = { animeId ->
                    navController.navigate("anime/$animeId")
                }
            )
        }

        composable(
            route = "anime/{animeId}",
            arguments = listOf(navArgument("animeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getInt("animeId") ?: return@composable
            val detailViewModelFactory = AnimeDetailsViewModel.Factory(detailUseCase, animeId)
            AnimeCardInfo(
                animeId = animeId,
                viewModelFactory = detailViewModelFactory,
                onAnimeClick = { id -> navController.navigate("anime/$id") }
            )
        }
    }
}