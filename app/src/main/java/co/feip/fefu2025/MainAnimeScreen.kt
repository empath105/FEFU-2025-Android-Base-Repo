package co.feip.fefu2025

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAnimeScreen(
    animee: List<RecomendAnimeData>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .background(Color.White)
            .fillMaxSize(),
    ) {
        TopAppBar(
            modifier = modifier
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
            modifier = modifier
                .background(Color.White)
                .fillMaxSize(),
        ) {
            items (animee) { anii ->
                AnimeCard(
                    title = anii.title,
                    rating = anii.rating,
                    genres = anii.genres,
                    image = anii.image,
                    year = anii.year,
                )

            }
            item {
                Spacer(modifier = modifier.height(8.dp))
            }

        }

    }
}

@Preview()
@Composable
fun PreviewMainAnimeScreen() {
    MainAnimeScreen(
        animee = listOf(
            RecomendAnimeData("GLEIPNIR", "6.9", listOf("DETECTIVE", "DARK FANTASY"), painterResource(id = R.drawable.gleipnir), "2020"),
            RecomendAnimeData("BUNGOU STRAY DOGS", "7.8", listOf("DETECTIVE", "SUPERNATURAL"), painterResource(id = R.drawable.bungostraydogs), "2016"),
            RecomendAnimeData("CHAINSAW MAN", "8.5", listOf("FANTASY", "ACTION", "HORROR"), painterResource(id = R.drawable.chainsawman), "2022"),
            RecomendAnimeData("ELFEN LIED", "7.8", listOf("ROMANCE", "DRAMA", "HORROR"), painterResource(id = R.drawable.elfenlied), "2004"),
            RecomendAnimeData("HAIKYUU!!", "8.4", listOf("SPORT", "COMEDY"), painterResource(id = R.drawable.huikui), "2014"),
            RecomendAnimeData("JUJUTSU KAISEN", "8.6", listOf("ACTION", "SUPERNATURAL"), painterResource(id = R.drawable.jujutsukaisen), "2020"),
            RecomendAnimeData("TENGOKU DAIMAKYOU", "8.2", listOf("FANTASY", "ADVENTURES"), painterResource(id = R.drawable.nebesnayastena), "2023"),
            RecomendAnimeData("NORAGAMI", "7.9", listOf("ACTION", "SUPERNATURAL"), painterResource(id = R.drawable.noragami), "2014"),
            RecomendAnimeData("MAHOU SHOUJO SITE", "6.5", listOf("ACTION", "DRAMA", "HORROR"), painterResource(id = R.drawable.saitvolshebnic), "2018"),
            RecomendAnimeData("BOCCHI THE ROCK!", "9.2", listOf("COMEDY", "MUSIC", "SLICE OF LIFE"), painterResource(id = R.drawable.bocchitherock), "2022"),
        )
    )
}
