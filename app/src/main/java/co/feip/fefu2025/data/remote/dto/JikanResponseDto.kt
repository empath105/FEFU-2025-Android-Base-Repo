package co.feip.fefu2025.data.remote.dto

import com.google.gson.annotations.SerializedName

data class JikanResponseDto<T>(
    @SerializedName("data") val data: T,
    @SerializedName("pagination") val pagination: PaginationDto?
)

data class AnimeDetailResponseDto(
    @SerializedName("data") val data: AnimeDto
)

data class AnimeRecommendationsResponseDto(
    @SerializedName("data") val data: List<RecommendationDto>
)

data class AnimeStatisticsResponseDto(
    @SerializedName("data") val data: StatisticsDataDto
)

data class PaginationDto(
    @SerializedName("has_next_page") val hasNextPage: Boolean
)

data class StatisticsDataDto(
    @SerializedName("scores") val scores: List<ScoreDto>?
)

data class ScoreDto(
    @SerializedName("score") val score: Int,
    @SerializedName("votes") val votes: Int
)