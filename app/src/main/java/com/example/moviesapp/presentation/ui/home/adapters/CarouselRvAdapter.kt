package com.example.moviesapp.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.common.MovieUiDiffUtil
import com.example.moviesapp.databinding.ItemNowPlayigMovieBinding
import com.example.moviesapp.domain.model.MovieUiModel

class CarouselRvAdapter : RecyclerView.Adapter<CarouselRvAdapter.CarouselViewHolder>() {

    private var imageList = emptyList<MovieUiModel>()

    inner class CarouselViewHolder(private val itemNowPlayigMovieBinding: ItemNowPlayigMovieBinding) :
        RecyclerView.ViewHolder(itemNowPlayigMovieBinding.root) {
        fun bind(movieUiModel: MovieUiModel) {
            itemNowPlayigMovieBinding.movieNowUrl = movieUiModel.posterImage
            itemNowPlayigMovieBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val layout =
            ItemNowPlayigMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CarouselViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val movieUi = imageList[position]
        holder.bind(movieUi)
    }

    fun updateList(newList: List<MovieUiModel>) {
        val diffUtil = MovieUiDiffUtil(imageList, newList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        imageList = newList
        diffResults.dispatchUpdatesTo(this)
    }

}