package com.example.moviesapp.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.common.ItemDiffUtilCallback
import com.example.moviesapp.databinding.ItemMovieBinding
import com.example.moviesapp.domain.model.MovieUiModel

class PopularMoviePagingAdapter :
    PagingDataAdapter<MovieUiModel, PopularMoviePagingAdapter.PopularMovieViewHolder>(
        ItemDiffUtilCallback
    ) {

    var onItemClick: (String) -> Unit = {}

    inner class PopularMovieViewHolder(private val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun bind(movieUiModel: MovieUiModel) {
            itemMovieBinding.movie = movieUiModel
            itemMovieBinding.executePendingBindings()

            itemMovieBinding.cardMovie.setOnClickListener {
                onItemClick(movieUiModel.id.toString())
            }
        }
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val layout = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PopularMovieViewHolder(layout)
    }

}