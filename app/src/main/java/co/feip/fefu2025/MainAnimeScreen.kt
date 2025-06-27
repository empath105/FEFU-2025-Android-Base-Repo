package co.feip.fefu2025

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import co.feip.fefu2025.presentation.viewmodels.AnimeListViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAnimeScreen(
    viewModel: AnimeListViewModel = viewModel(),
    onAnimeClick: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val animeList = viewModel.animeList
    Column (
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
    ) {
        TopAppBar(
            modifier = Modifier
                .shadow(elevation = 10.dp)
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(20.dp))
                .height(55.dp),

            title = {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Поиск",
                        fontSize = 20.sp,
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
        ) {
            items (animeList) { an ->
                an.year?.let{
                    AnimeCard(
                        title = an.title,
                        rating = an.rating,
                        genres = an.genres,
                        image = painterResource(id = an.imageResId),
                        year = it,
                        modifier = Modifier
                            .clickable { onAnimeClick(an.id) }
                    )
                }

            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

        }

    }
}
