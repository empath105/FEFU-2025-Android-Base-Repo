package co.feip.fefu2025

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun RaitingTable(
    raiting: List<Int>,
    modifier: Modifier = Modifier
) {
    val maxRating = raiting.maxOrNull() ?: 0
    val colors = listOf(
        Color(0xFFFF3333),
        Color(0xFFFB745C),
        Color(0xFFFD9785),
        Color(0xFFFFB489),
        Color(0xFFFFC489),
        Color(0xFFF8E492),
        Color(0xFFF8F692),
        Color(0xFFDDFD88),
        Color(0xFFCCFF99),
        Color(0xFF99FF66),
    )
    Column(
        modifier = modifier
            .shadow(elevation = 10.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(red = 248, green = 248, blue = 255))
            .height(350.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom

        ) {
            for (i in 1..10) {
                val height = if (maxRating > 0) {
                    (raiting[i - 1] / maxRating.toFloat() * 250).dp // Высота столбца
                } else {
                    0.dp
                }


                Column(
                    modifier = Modifier
                        .width(30.dp)
                        .align(Alignment.Bottom)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom,

                ) {
                    Text(
                        text = (raiting[i - 1]).toString(),
                        color = Color.Gray,
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Column(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(colors[i-1])
                            .fillMaxWidth()
                            .height(height),
                    ){}
                    Text(
                        text = i.toString(),
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                    )
                }

            }
        }

    }
}


@Preview()
@Composable
fun PreviewRaitingTable() {
    RaitingTable(
        raiting = listOf(100, 80, 180, 220, 260, 270, 300, 340, 450, 500),


    )
}