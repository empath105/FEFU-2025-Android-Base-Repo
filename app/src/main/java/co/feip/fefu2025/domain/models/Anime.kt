package co.feip.fefu2025.domain.models

data class Anime(
    val id: Int,
    val title: String,
    val rating: String,
    val genres: List<String>,
    val imageUrl: String?,
    val year: String? = null,
    val season: String? = null,
    val episodes: String? = null,
    val description: String? = null,
    val ratings: List<Int> = emptyList(),
    val recommendationIds: List<Int> = emptyList(),
    val recommendations: List<Anime> = emptyList()
)