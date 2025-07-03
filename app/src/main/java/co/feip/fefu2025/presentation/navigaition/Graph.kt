package co.feip.fefu2025.presentation.navigaition

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import co.feip.fefu2025.MainAnimeScreen
import co.feip.fefu2025.MainRecommendationScreen
import co.feip.fefu2025.AnimeCardInfo
import co.feip.fefu2025.MainSearchScreen
import co.feip.fefu2025.data.repository.AnimeRepositoryI
import co.feip.fefu2025.domain.usecases.GetAnimeDetailsUseCase
import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
import co.feip.fefu2025.domain.usecases.GetGlobalRecommendationsUseCase
import co.feip.fefu2025.domain.usecases.GetSearchUseCase
import co.feip.fefu2025.presentation.viewmodels.AnimeListViewModel
import co.feip.fefu2025.presentation.viewmodels.AnimeDetailsViewModel
import co.feip.fefu2025.presentation.viewmodels.RecommendationsViewModel
import co.feip.fefu2025.presentation.viewmodels.SearchViewModel


@Composable
fun Graph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val repository = AnimeRepositoryI()
    val listUseCase = GetAnimeListUseCase(repository)
    val detailUseCase = GetAnimeDetailsUseCase(repository)
    val recommendationsUseCase = GetGlobalRecommendationsUseCase(repository)
    val searchUseCase = GetSearchUseCase(repository)

    NavHost(
        navController = navController,
        startDestination = "main",
        modifier = modifier
    ) {
        composable("main") {
            val viewModel: AnimeListViewModel = viewModel(
                factory = AnimeListViewModel.Factory(listUseCase)
            )
            MainAnimeScreen(
                navController = navController,
                viewModel = viewModel,
                onAnimeClick = { id -> navController.navigate("anime/$id") }
            )
        }

        composable(
            route = "anime/{animeId}",
            arguments = listOf(navArgument("animeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val animeId = backStackEntry.arguments?.getInt("animeId") ?: return@composable
            val viewModelFactory = AnimeDetailsViewModel.Factory(detailUseCase, animeId)

            AnimeCardInfo(
                animeId = animeId,
                viewModelFactory = viewModelFactory,
                onAnimeClick = { id -> navController.navigate("anime/$id") },
                onRecommendationsClick = { excludedId ->
                    navController.navigate("recommendations/$excludedId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = "recommendations/{excludeAnimeId}",
            arguments = listOf(navArgument("excludeAnimeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val excludeAnimeId = backStackEntry.arguments?.getInt("excludeAnimeId") ?: 0
            val viewModel: RecommendationsViewModel = viewModel(
                factory = RecommendationsViewModel.Factory(
                    useCase = recommendationsUseCase,
                    excludeAnimeId = excludeAnimeId
                )
            )

            MainRecommendationScreen(
                viewModel = viewModel,
                onAnimeClick = { id -> navController.navigate("anime/$id") },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("search") {
            val viewModel: SearchViewModel = viewModel(
                factory = SearchViewModel.Factory(searchUseCase)
            )
            MainSearchScreen(
                onBackClick = { navController.popBackStack() },
                onAnimeClick = { id -> navController.navigate("anime/$id") },
                viewModel = viewModel
            )
        }
    }
}