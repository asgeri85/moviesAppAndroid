package com.example.moviesapp.presentation.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ItemCastBinding
import com.example.moviesapp.domain.model.CastingUiModel

class CastAdapter : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {
    inner class CastViewHolder(private val castBinding: ItemCastBinding) :
        RecyclerView.ViewHolder(castBinding.root) {
        fun bind(castingUiModel: CastingUiModel) {
            castBinding.cast = castingUiModel
            castBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layout = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CastViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    object CastDiffUtilCallback : DiffUtil.ItemCallback<CastingUiModel>() {
        override fun areItemsTheSame(oldItem: CastingUiModel, newItem: CastingUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CastingUiModel, newItem: CastingUiModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, CastDiffUtilCallback)


}