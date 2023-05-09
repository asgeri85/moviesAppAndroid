package com.example.moviesapp.common.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.moviesapp.common.ImageTypeEnum

object BindingAdapter {

    @BindingAdapter("load_image")
    @JvmStatic
    fun loadImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            imageView.loadImageUrl(imageUrl, ImageTypeEnum.MOVIE_IMAGE)
        }
    }

    @BindingAdapter("load_youtube")
    @JvmStatic
    fun loadYoutube(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            imageView.loadImageUrl(imageUrl, ImageTypeEnum.YOUTUBE_IMAGE)
        }
    }

    @BindingAdapter("load_user")
    @JvmStatic
    fun loadUserImage(imageView: ImageView, imageUrl: String?) {
        imageUrl?.let {
            imageView.loadImageUrl(imageUrl.removePrefix("/"), ImageTypeEnum.USER_IMAGE)
        }
    }
}