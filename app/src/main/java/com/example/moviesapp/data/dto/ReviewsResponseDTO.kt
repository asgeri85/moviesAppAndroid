package com.example.moviesapp.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewsResponseDTO(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<ReviewsDTO?>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
)