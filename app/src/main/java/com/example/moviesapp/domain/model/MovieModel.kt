package com.example.moviesapp.domain.model

import android.os.Parcelable
import com.example.moviesapp.common.MovieTypeEnum
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val type: MovieTypeEnum,
    val title: String
) : Parcelable
