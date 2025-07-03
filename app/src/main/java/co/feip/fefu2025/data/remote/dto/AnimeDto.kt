package co.feip.fefu2025.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AnimeDto(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("images") val images: AnimeImagesDto,
    @SerializedName("score") val score: Double?,
    @SerializedName("genres") val genres: List<GenreDto>,
    @SerializedName("year") val year: Int?,
    @SerializedName("season") val season: String?,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("synopsis") val synopsis: String?
)

data class AnimeImagesDto(
    @SerializedName("jpg") val jpg: ImageUrlsDto
)

data class ImageUrlsDto(
    @SerializedName("image_url") val imageUrl: String?
)

data class GenreDto(
    @SerializedName("name") val name: String
)

data class RecommendationDto(
    @SerializedName("entry") val entry: AnimeDto
)