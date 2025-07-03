package co.feip.fefu2025

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import co.feip.fefu2025.presentation.viewmodels.RecommendationsViewModel
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainRecommendationScreen(
    viewModel: RecommendationsViewModel = viewModel(),
    onAnimeClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val recomendList by viewModel.recomendList
    val isLoading by viewModel.isLoading
    val error by viewModel.error

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Может понравиться",
                    fontSize = 20.sp,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                }
            }
        )

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            error != null -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(error ?: "Произошла ошибка", color = Color.Red)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.loadRecommendations() }) {
                        Text("Повторить")
                    }
                }
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize(),
                ) {
                    items(recomendList) { anime ->
                        anime.year?.let {
                            AnimeCard(
                                title = anime.title,
                                rating = anime.rating,
                                genres = anime.genres,
                                imageUrl = anime.imageUrl,
                                year = it,
                                modifier = Modifier
                                    .clickable { onAnimeClick(anime.id) }
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
