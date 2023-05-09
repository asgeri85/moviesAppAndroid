package com.example.moviesapp.presentation.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.showToast
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentExploreBinding
import com.example.moviesapp.presentation.ui.movieList.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private val binding: FragmentExploreBinding by viewBinding(FragmentExploreBinding::bind)
    private val viewModel by viewModels<ExploreViewModel>()
    private val exploreAdapter = MovieListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecycler()
        observeData()
        searchTextChangeListener()

        binding.imageButton.setOnClickListener {
            findNavController().navigate(ExploreFragmentDirections.actionExploreFragmentToFilterBottomFragment())
        }

    }

    private fun observeData() {
        viewModel.explorePagingData.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                exploreAdapter.submitData(it)

                exploreAdapter.loadStateFlow.collectLatest {
                    when (it.refresh) {
                        is LoadState.Loading -> {
                            binding.exploreLoading.visible()
                        }

                        is LoadState.NotLoading -> {
                            binding.rvExplore.visible()
                            binding.exploreLoading.gone()
                            binding.emptyExplore.gone()
                            if (it.append.endOfPaginationReached && exploreAdapter.itemCount == 0) {
                                binding.rvExplore.gone()
                                binding.emptyExplore.visible()
                            }
                        }

                        is LoadState.Error -> {
                            (it.refresh as LoadState.Error).error.localizedMessage?.let { it1 ->
                                requireActivity().showToast(
                                    "Error",
                                    it1, MotionToastStyle.ERROR
                                )
                            }
                        }
                    }
                }

            }
        }
    }

    private fun setRecycler() {
        binding.rvExplore.adapter = exploreAdapter
    }

    private fun searchTextChangeListener() {
        binding.editSearchExplore.addTextChangedListener {
            it?.let {
                if (it.length >= 2) {
                    viewModel.searchMovieData(it.toString())
                } else {
                    viewModel.getMovieData()
                }
            }
        }
    }

}