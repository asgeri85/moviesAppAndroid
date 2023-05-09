package com.example.moviesapp.presentation.ui.movieList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import com.example.moviesapp.R
import com.example.moviesapp.common.MovieTypeEnum
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.showToast
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentMovieListBinding
import com.example.moviesapp.presentation.ui.home.adapters.PopularMoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private val binding: FragmentMovieListBinding by viewBinding(FragmentMovieListBinding::bind)
    private val viewModel by viewModels<MovieListViewModel>()
    private val args: MovieListFragmentArgs by navArgs()
    private val movieAdapter = MovieListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        getMoviesData()
        observeData()

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun getMoviesData() {
        binding.topAppBar.title = args.movieType.title
        when (args.movieType.type) {
            MovieTypeEnum.POPULAR_MOVIES -> viewModel.getPopularMovies()
            MovieTypeEnum.TOP_RATED_MOVIES -> viewModel.getTopRatedMovies()
            else -> {}
        }
    }

    private fun observeData() {
        viewModel.pagingDataMovies.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                movieAdapter.submitData(it)

                movieAdapter.loadStateFlow.collectLatest {
                    when (it.refresh) {
                        is LoadState.Loading -> {
                            binding.filmListLoading.visible()
                        }

                        is LoadState.NotLoading -> {
                            binding.filmListLoading.gone()
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

    private fun setRecyclerView() {
        binding.rvMovieList.adapter = movieAdapter
    }
}