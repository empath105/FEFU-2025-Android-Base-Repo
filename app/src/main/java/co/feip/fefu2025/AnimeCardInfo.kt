package co.feip.fefu2025

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.foundation.lazy.items


@Composable
fun AnimeCardInfo(
    title: String,
    rating: String,
    genres: List<String>,
    image: Painter,
    year: String,
    season: String,
    episodes: String,
    info: String,
    raiting: List<Int>,
    recomend: List<RecomendAnimeData>,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()

    ){
        item {
            Image(
                painter = image,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .shadow(elevation = 15.dp)
                    .clip(RoundedCornerShape(bottomEnd = 20.dp)),

                )

            Spacer(modifier = modifier.height(30.dp))

            Text(
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Season $season · $episodes eps",
                fontSize = 14.sp,
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.height(10.dp))

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
                    genres = genres,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp, 0.dp, 0.dp),

                    )
            }

            Spacer(modifier = modifier.height(10.dp))

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
                Text(
                    text = year,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),

                    )
            }

            Spacer(modifier = modifier.height(10.dp))

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
                    text = "$rating/10",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),

                    )
            }

            Spacer(modifier = modifier.height(10.dp))

            Text(
                text = "Описание:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )

            Spacer(modifier = modifier.height(8.dp))

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(red = 248, green = 248, blue = 255))
            ){
                Text(
                    text = info,
                    fontSize = 16.sp,
                    color = Color.Gray,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 13.dp),

                    )

            }

            Spacer(modifier = modifier.height(15.dp))
            Text(
                text = "Оценки людей:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )

            Spacer(modifier = modifier.height(8.dp))

            Column (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .clip(RoundedCornerShape(20.dp))

            ) {
                RaitingTable(raiting)
            }

            Spacer(modifier = modifier.height(15.dp))

            Text(
                text = "Может понравиться:",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(recomend) { anime ->
                    AnimeCard(
                        title = anime.title,
                        rating = anime.rating,
                        genres = anime.genres,
                        image = anime.image,
                        year = anime.year,
                    )
                }
            }

            Spacer(modifier = modifier.height(15.dp))


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


        // Создание и установка фона с округленными углами
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


@Preview()
@Composable
fun PreviewAnimeCardInfo() {
    AnimeCardInfo(
        title = "BOCCHI THE ROCK!",
        rating = "9.2",
        genres = listOf("Комедия", "Музыка", "Повседневность"),
        image = painterResource(id = R.drawable.bocchitherock),
        year = "2022",
        season = "1",
        episodes = "12",
        info = "Хитори Гото с детства мечтает играть в рок-группе" +
                " и ради этого чуть ли не в совершенстве овладела " +
                "игрой на электрогитаре. К несчастью, исключительные " +
                "навыки так и не принесли ей ни единого друга. Однако, " +
                "возможно, её мечта осуществится благодаря встрече с " +
                "Нидзикой Идзити — девушкой, которая играет на ударных и " +
                "ищет гитариста для своей группы…",

        raiting = listOf(100, 80, 180, 220, 260, 270, 300, 340, 450, 500),
        recomend = listOf(
            RecomendAnimeData("GLEIPNIR", "6.9", listOf("DETECTIVE", "DARK FANTASY"), painterResource(id = R.drawable.gleipnir), "2020"),
            RecomendAnimeData("BUNGOU STRAY DOGS", "7.8", listOf("DETECTIVE", "SUPERNATURAL"), painterResource(id = R.drawable.bungostraydogs), "2016"),
            RecomendAnimeData("CHAINSAW MAN", "8.5", listOf("FANTASY", "ACTION", "HORROR"), painterResource(id = R.drawable.chainsawman), "2022"),
            RecomendAnimeData("ELFEN LIED", "7.8", listOf("ROMANCE", "DRAMA", "HORROR"), painterResource(id = R.drawable.elfenlied), "2004"),
            RecomendAnimeData("HAIKYUU!!", "8.4", listOf("SPORT", "COMEDY"), painterResource(id = R.drawable.huikui), "2014"),
            RecomendAnimeData("JUJUTSU KAISEN", "8.6", listOf("ACTION", "SUPERNATURAL"), painterResource(id = R.drawable.jujutsukaisen), "2020"),
            RecomendAnimeData("TENGOKU DAIMAKYOU", "8.2", listOf("FANTASY", "ADVENTURES"), painterResource(id = R.drawable.nebesnayastena), "2023"),
            RecomendAnimeData("NORAGAMI", "7.9", listOf("ACTION", "SUPERNATURAL"), painterResource(id = R.drawable.noragami), "2014"),
            RecomendAnimeData("MAHOU SHOUJO SITE", "6.5", listOf("ACTION", "DRAMA", "HORROR"), painterResource(id = R.drawable.saitvolshebnic), "2018"),
            RecomendAnimeData("KAKEGURUI", "7.2", listOf("DRAMA", "THRILLER", "DETECTIVE"), painterResource(id = R.drawable.kakegurui), "2017"),
        )
    )
}
