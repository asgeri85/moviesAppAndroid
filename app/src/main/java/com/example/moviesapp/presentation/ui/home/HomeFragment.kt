package com.example.moviesapp.presentation.ui.home

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.moviesapp.R
import com.example.moviesapp.common.MovieTypeEnum
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.Constants.BASE_URL_IMAGE
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.showToast
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentHomeBinding
import com.example.moviesapp.domain.model.MovieModel
import com.example.moviesapp.domain.model.MovieUiModel
import com.example.moviesapp.presentation.ui.home.adapters.CarouselRvAdapter
import com.example.moviesapp.presentation.ui.home.adapters.PopularMoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import www.sanju.motiontoast.MotionToastStyle
import kotlin.math.abs


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private val carouselRvAdapter = CarouselRvAdapter()
    private val popularMoviePagingAdapter = PopularMoviePagingAdapter()
    private val topRatedMoviePagingAdapter = PopularMoviePagingAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPagerCarousel()
        setRecycler()
        observeState()
        navigateItemClick()
    }

    private fun observeState() {
        viewModel.homeUiStateData.observe(viewLifecycleOwner) {
            when (it) {
                is HomeUiState.SuccessNowPlaying -> {
                    carouselRvAdapter.updateList(it.data)
                    setImageSlider(it.data.take(5))
                    binding.homeLoading.gone()
                }

                is HomeUiState.Error -> {
                    binding.homeLoading.gone()
                    requireActivity().showToast("Error", it.message, MotionToastStyle.ERROR)
                }

                is HomeUiState.Loading -> {
                    binding.homeLoading.visible()
                }

            }
        }

        viewModel.pagingDataPopular.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                popularMoviePagingAdapter.submitData(it)
            }
        }

        viewModel.pagingDataTopRated.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                topRatedMoviePagingAdapter.submitData(it)
            }
        }
    }

    private fun setViewPagerCarousel() {
        with(binding.viewPageCarouselHome) {
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = carouselRvAdapter
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer((16 * Resources.getSystem().displayMetrics.density).toInt()))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = (0.85f + r * 0.15f)
        }
        binding.viewPageCarouselHome.setPageTransformer(compositePageTransformer)
    }

    private fun setRecycler() {
        binding.rvPopularMovie.adapter = popularMoviePagingAdapter
        binding.rvTopRated.adapter = topRatedMoviePagingAdapter
    }

    private fun setImageSlider(list: List<MovieUiModel>) {
        val imageList = arrayListOf<SlideModel>()
        list.forEach {
            val model = SlideModel(BASE_URL_IMAGE + it.backImage, ScaleTypes.FIT)
            imageList.add(model)
        }
        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
    }

    private fun navigateItemClick() {
        binding.textSeeAllTopMovie.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToMovieListFragment(
                    MovieModel(
                        MovieTypeEnum.POPULAR_MOVIES,
                        binding.textView5.text.toString()
                    )
                )
            )
        }

        binding.textViewTopRated.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToMovieListFragment(
                    MovieModel(
                        MovieTypeEnum.TOP_RATED_MOVIES,
                        binding.textView9.text.toString()
                    )
                )
            )
        }

        popularMoviePagingAdapter.onItemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    it
                )
            )
        }

        topRatedMoviePagingAdapter.onItemClick = {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    it
                )
            )
        }
    }

}