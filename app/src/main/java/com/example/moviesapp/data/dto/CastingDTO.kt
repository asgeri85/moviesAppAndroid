package com.example.moviesapp.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastingDTO(
    @Json(name = "cast")
    val cast: List<CastDTO?>?,
    @Json(name = "crew")
    val crew: List<CrewDTO?>?,
    @Json(name = "id")
    val id: Int?
)