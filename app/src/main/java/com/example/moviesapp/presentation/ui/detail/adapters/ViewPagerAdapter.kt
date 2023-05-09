package com.example.moviesapp.presentation.ui.detail.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moviesapp.presentation.ui.detail.CommentsDetailFragment
import com.example.moviesapp.presentation.ui.detail.MoreLikeDetailFragment
import com.example.moviesapp.presentation.ui.detail.TrailersDetailFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val id: Int
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TrailersDetailFragment(id)
            1 -> MoreLikeDetailFragment(id)
            else -> CommentsDetailFragment(id)
        }
    }

}