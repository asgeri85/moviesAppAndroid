package com.example.moviesapp.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthorDetails(
    @Json(name = "avatar_path")
    val avatarPath: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "rating")
    val rating: Double?,
    @Json(name = "username")
    val username: String?
)