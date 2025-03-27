package co.feip.fefu2025

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {

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
//            color = Color(0xFF9370DB),
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

        Spacer(modifier = modifier.height(10.dp))

        Text(
            text = info,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),


            )

        Spacer(modifier = modifier.height(15.dp))


    }

}

@Composable
fun FlexBoxLayoutGenre(
    genres: List<String>,
    modifier: Modifier = Modifier
){
    AndroidView(
        factory = { context -> FlexBoxLayout(context).apply {
                genres.forEach { genre -> addView(createGenreTextView(genre, context)) }
            }
        },
        modifier = modifier,
    )

}

fun createGenreTextView(genre: String, context: Context): MyView {
    return MyView(context).apply {
        setGenreName(genre)
        val backgroundColor = randomBackground().toArgb()
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
                "ищет гитариста для своей группы…"

    )
}