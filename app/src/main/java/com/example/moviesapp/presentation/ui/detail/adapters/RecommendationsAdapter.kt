package com.example.moviesapp.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.common.ItemDiffUtilCallback
import com.example.moviesapp.databinding.ItemMovieListBinding
import com.example.moviesapp.domain.model.MovieUiModel

class RecommendationsAdapter :
    RecyclerView.Adapter<RecommendationsAdapter.RecommendationsViewHolder>() {
    inner class RecommendationsViewHolder(private val itemMovieListBinding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(itemMovieListBinding.root) {
        fun bind(movieUiModel: MovieUiModel) {
            itemMovieListBinding.movie = movieUiModel
            itemMovieListBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationsViewHolder {
        val layout =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecommendationsViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecommendationsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    val differ = AsyncListDiffer(this, ItemDiffUtilCallback)
}