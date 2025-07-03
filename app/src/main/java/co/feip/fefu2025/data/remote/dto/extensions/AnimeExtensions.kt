package co.feip.fefu2025.data.remote.dto.extensions

import co.feip.fefu2025.data.remote.dto.*
import co.feip.fefu2025.domain.models.Anime

fun AnimeDto.toDomain(): Anime = Anime(
    id = this.id,
    title = this.title ?: "Без названия",
    rating = this.score?.toString() ?: "N/A",
    genres = this.genres?.map { it.name } ?: emptyList(),
    imageUrl = this.images.jpg.imageUrl ?: "",
    year = this.year?.toString(),
    season = this.season,
    episodes = this.episodes?.toString(),
    description = this.synopsis,
    ratings = emptyList(),
    recommendationIds = emptyList(),
    recommendations = emptyList()
)

fun StatisticsDataDto.toRatings(): List<Int> =
    this.scores?.mapNotNull { it.votes } ?: emptyList()