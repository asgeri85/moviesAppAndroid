package com.example.moviesapp.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ItemReviewBinding
import com.example.moviesapp.domain.model.ReviewUiModel

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    inner class ReviewViewHolder(private val itemReviewBinding: ItemReviewBinding) :
        RecyclerView.ViewHolder(itemReviewBinding.root) {
        fun bind(reviewUiModel: ReviewUiModel) {
            itemReviewBinding.review = reviewUiModel
            itemReviewBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layout = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ReviewViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object ReviewDiffUtilCallback : DiffUtil.ItemCallback<ReviewUiModel>() {
        override fun areItemsTheSame(oldItem: ReviewUiModel, newItem: ReviewUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReviewUiModel, newItem: ReviewUiModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, ReviewDiffUtilCallback)
}