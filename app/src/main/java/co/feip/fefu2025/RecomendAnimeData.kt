package co.feip.fefu2025

import androidx.compose.ui.graphics.painter.Painter

data class RecomendAnimeData(
    val title: String,
    val rating: String,
    val genres: List<String>,
    val image: Painter,
    val year: String
)