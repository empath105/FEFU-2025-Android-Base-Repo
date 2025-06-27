package co.feip.fefu2025.presentation.previes

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Devices
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import co.feip.fefu2025.presentation.viewmodels.AnimeListViewModel
import co.feip.fefu2025.presentation.viewmodels.AnimeDetailsViewModel
import co.feip.fefu2025.MainAnimeScreen
import co.feip.fefu2025.AnimeCardInfo
import co.feip.fefu2025.data.repository.AnimeRepositoryI
import co.feip.fefu2025.domain.usecases.GetAnimeDetailsUseCase
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import androidx.lifecycle.viewmodel.compose.viewModel

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PreviewAppNavigation() {
    PreviewNavGraph()
}

@Composable
fun PreviewNavGraph() {
    val navController = rememberNavController()
    val repository = AnimeRepositoryI() // Мок-репозиторий
    val listUseCase = GetAnimeListUseCase(repository)
    val detailUseCase = GetAnimeDetailsUseCase(repository)

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            val viewModel: AnimeListViewModel = viewModel(
                factory = AnimeListViewModel.Factory(listUseCase)
            )
            MainAnimeScreen(
                viewModel = viewModel,
                onAnimeClick = { id -> navController.navigate("anime/$id") }
            )
        }
        composable(
            "anime/{animeId}",
            arguments = listOf(navArgument("animeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getInt("animeId") ?: 1
            val viewModel: AnimeDetailsViewModel = viewModel(
                factory = AnimeDetailsViewModel.Factory(detailUseCase, animeId)
            )
            AnimeCardInfo(
                animeId = animeId,
                viewModelFactory = AnimeDetailsViewModel.Factory(detailUseCase, animeId),
                onAnimeClick = { id -> navController.navigate("anime/$id") }
            )
        }
    }
}