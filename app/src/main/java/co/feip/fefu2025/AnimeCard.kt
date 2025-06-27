package co.feip.fefu2025

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnimeCard(
    title: String,
    rating: String,
    genres: List<String>,
    image: Painter,
    year: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(5.dp)
            .height(290.dp)
            .width(185.dp),
    elevation = CardDefaults.cardElevation(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(red = 245, green = 245, blue = 245)
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ){
                Image(
                    painter = image,
                    contentDescription = title,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(225.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )


                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(Color.Black.copy(alpha = 0.8f), RoundedCornerShape(topEnd = 8.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = "★ $rating",
                        color = Color.Yellow,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                    )
                }
            }

            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "genre: ",
                    fontSize = 11.sp,
                    )
                Text(
                    text = genres.joinToString(", "),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    )
            }

            Text(
                text = year,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                textAlign = TextAlign.Center,

            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewAnimeScreen() {
    AnimeCard(
        title = "ELFEN LIED",
        rating = "7.8",
        genres = listOf("ROMANCE", "DRAMA", "HORROR"),
        image = painterResource(id = R.drawable.elfenlied),
        year = "2004"
    )
}