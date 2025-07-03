package co.feip.fefu2025

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import co.feip.fefu2025.presentation.viewmodels.AnimeDetailsViewModel
import co.feip.fefu2025.domain.models.Anime
import androidx.compose.runtime.getValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil.compose.AsyncImage

@Composable
fun AnimeCardInfo(
    animeId: Int,
    viewModelFactory: AnimeDetailsViewModel.Factory,
    onAnimeClick: (Int) -> Unit,
    onRecommendationsClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val viewModel: AnimeDetailsViewModel = viewModel(factory = viewModelFactory)
    val anime by viewModel.anime
    val isLoading by viewModel.isLoading
    val error by viewModel.error
    val areRecommendationsLoading = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
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
                    Button(onClick = { viewModel.loadAnimeData()
                        viewModel.loadRecommendations()}) {
                        Text("Повторить")
                    }
                }
            }
            anime != null -> {
                AnimeScreenContent(
                    anime = anime!!,
                    areRecommendationsLoading = areRecommendationsLoading.value,
                    onAnimeClick = onAnimeClick,
                    onRecommendationsClick = {
                        areRecommendationsLoading.value = true
                        onRecommendationsClick(anime!!.id)
                    },
                    onBackClick = onBackClick
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeScreenContent(
    anime: Anime,
    onAnimeClick: (Int) -> Unit,
    onRecommendationsClick: () -> Unit,
    onBackClick: () -> Unit,
    areRecommendationsLoading: Boolean
) {
    val scrollState = rememberScrollState()


    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ){
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = anime.imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                            .shadow(elevation = 15.dp)
                            .clip(RoundedCornerShape(bottomEnd = 20.dp))
                    )

                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .padding(16.dp)
                            .background(
                                color = Color.Black.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(50)
                            )
                            .size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Назад",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = anime.title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Season ${anime.season} · ${anime.episodes} eps",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Жанры:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    FlexBoxLayoutGenre(
                        genres = anime.genres,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp, 0.dp, 0.dp, 0.dp),

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Год выпуска:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    anime.year?.let{
                        Text(
                            text = it,
                            fontSize = 16.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),

                            )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Рейтинг:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "${anime.rating}/10",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),

                        )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Описание:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(color = Color(red = 248, green = 248, blue = 255))
                ){
                    anime.description?.let{
                        Text(
                            text = it,
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 13.dp),

                            )
                    }

                }

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Оценки людей:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .clip(RoundedCornerShape(20.dp))

                ) {
                    anime.ratings.takeIf { it.isNotEmpty() }?.let { ratings ->
                        RaitingTable(ratings)
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Может понравиться:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clickable { onRecommendationsClick() }
                )
                if (areRecommendationsLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (anime.recommendations.isEmpty()) {
                    Text(
                        text = "Рекомендации не найдены",
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        items(anime.recommendations.filter {
                            it.title.isNotEmpty() && it.imageUrl?.isNotEmpty() == true
                        }) { recommendation ->
                            AnimeCard(
                                title = recommendation.title,
                                rating = recommendation.rating,
                                genres = recommendation.genres,
                                imageUrl = recommendation.imageUrl,
                                year = recommendation.year ?: "N/A",
                                modifier = Modifier
                                    .width(160.dp)
                                    .clickable { onAnimeClick(recommendation.id) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))


            }

        }
    }
}

@Composable
fun FlexBoxLayoutGenre(
    genres: List<String>,
    modifier: Modifier = Modifier
){
    AndroidView(
        factory = { context -> FlexBoxLayout(context).apply {
                genres.forEachIndexed { index, genre -> addView(createGenreTextView(genre, context, index)) }
            }
        },
        modifier = modifier,
    )

}

fun createGenreTextView(genre: String, context: Context, index: Int): MyView {
    return MyView(context).apply {
        setGenreName(genre)
        val backgroundColor = (pastelColors[index % pastelColors.size]).toArgb()
        val cornerRadius = resources.getDimension(R.dimen.corner_radius)

        val backgroundDrawable = GradientDrawable().apply {
            setColor(backgroundColor)
            setCornerRadius(cornerRadius)
            setStroke(4, Color.Gray.toArgb())
        }
        background = backgroundDrawable
        val params = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT).apply {
            setMargins(0, 2.toPx(context), 10.toPx(context), 10.toPx(context))
        }
        layoutParams = params
    }

}

private fun Int.toPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}