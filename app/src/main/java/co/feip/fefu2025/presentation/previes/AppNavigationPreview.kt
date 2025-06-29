package co.feip.fefu2025.presentation.previes
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.tooling.preview.Devices
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.NavType
//import androidx.navigation.navArgument
//import androidx.navigation.navDeepLink
//import co.feip.fefu2025.MainAnimeScreen
//import co.feip.fefu2025.MainRecommendationScreen
//import co.feip.fefu2025.AnimeCardInfo
//import co.feip.fefu2025.MainSearchScreen
//import co.feip.fefu2025.data.repository.AnimeRepositoryI
//import co.feip.fefu2025.domain.models.Anime
//import co.feip.fefu2025.domain.usecases.GetAnimeDetailsUseCase
//import co.feip.fefu2025.domain.usecases.GetAnimeListUseCase
//import co.feip.fefu2025.domain.usecases.GetGlobalRecommendationsUseCase
//import co.feip.fefu2025.domain.usecases.GetSearchUseCase
//import co.feip.fefu2025.presentation.viewmodels.AnimeListViewModel
//import co.feip.fefu2025.presentation.viewmodels.AnimeDetailsViewModel
//import co.feip.fefu2025.presentation.viewmodels.RecommendationsViewModel
//import co.feip.fefu2025.presentation.viewmodels.SearchViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//
//
//@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_4)
//@Composable
//fun FullAppPreview() {
//    val navController = rememberNavController()
//    val repository = AnimeRepositoryI()
//    val listUseCase = GetAnimeListUseCase(repository)
//    val detailUseCase = GetAnimeDetailsUseCase(repository)
//    val recommendationsUseCase = GetGlobalRecommendationsUseCase(repository)
//
//    NavHost(
//        navController = navController,
//        startDestination = "main"
//    ) {
//        composable("main") {
//            val viewModel: AnimeListViewModel = viewModel(
//                factory = AnimeListViewModel.Factory(listUseCase)
//            )
//            MainAnimeScreen(
//                navController = navController,
//                viewModel = viewModel,
//                onAnimeClick = { id -> navController.navigate("anime/$id") }
//            )
//        }
//
//        composable(
//            "anime/{animeId}",
//            arguments = listOf(navArgument("animeId") { type = NavType.IntType }),
//            deepLinks = listOf(navDeepLink { uriPattern = "mysuperapp://anime/{animeId}" })
//        ) { backStackEntry ->
//            val animeId = backStackEntry.arguments?.getInt("animeId") ?: 1
//            val viewModel: AnimeDetailsViewModel = viewModel(
//                factory = AnimeDetailsViewModel.Factory(detailUseCase, animeId)
//            )
//            AnimeCardInfo(
//                animeId = animeId,
//                viewModelFactory = AnimeDetailsViewModel.Factory(detailUseCase, animeId),
//                onAnimeClick = { id -> navController.navigate("anime/$id") },
//                onRecommendationsClick = { excludedId ->
//                    navController.navigate("recommendations/$excludedId") },
//                onBackClick = { navController.popBackStack() }
//            )
//        }
//
//        composable("recommendations/{excludeAnimeId}") { backStackEntry ->
//            val excludeAnimeId = backStackEntry.arguments?.getString("excludeAnimeId")?.toIntOrNull()
//            val viewModel: RecommendationsViewModel = viewModel(
//                factory = RecommendationsViewModel.Factory(
//                    useCase = recommendationsUseCase,
//                    excludeAnimeId = excludeAnimeId
//                )
//            )
//            MainRecommendationScreen(
//                viewModel = viewModel,
//                onAnimeClick = { id -> navController.navigate("anime/$id") },
//                onBackClick = { navController.popBackStack() }
//            )
//        }
//    }
//}