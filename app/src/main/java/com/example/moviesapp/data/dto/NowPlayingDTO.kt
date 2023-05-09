package com.example.moviesapp.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NowPlayingDTO(
    @Json(name = "dates")
    val dates: DatesDTO?,
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<MovieResponseDTO?>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
)