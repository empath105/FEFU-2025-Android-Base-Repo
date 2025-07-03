package co.feip.fefu2025.data.remote

import co.feip.fefu2025.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanApiService {
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int = 1
    ): JikanResponseDto<List<AnimeDto>>

    @GET("anime/{id}/full")
    suspend fun getAnimeDetails(
        @Path("id") id: Int
    ): AnimeDetailResponseDto

    @GET("anime/{id}/recommendations")
    suspend fun getRecommendations(
        @Path("id") id: Int
    ): AnimeRecommendationsResponseDto

    @GET("anime/{id}/statistics")
    suspend fun getStatistics(
        @Path("id") id: Int
    ): AnimeStatisticsResponseDto

    @GET("anime")
    suspend fun searchAnime(
        @Query("q") query: String,
        @Query("page") page: Int = 1
    ): JikanResponseDto<List<AnimeDto>>
}