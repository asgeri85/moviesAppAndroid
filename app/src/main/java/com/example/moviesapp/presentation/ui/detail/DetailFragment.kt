package com.example.moviesapp.presentation.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.showToast
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentDetailBinding
import com.example.moviesapp.presentation.ui.detail.adapters.CastAdapter
import com.example.moviesapp.presentation.ui.detail.adapters.GenreAdapter
import com.example.moviesapp.presentation.ui.detail.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding: FragmentDetailBinding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel by viewModels<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()
    private val genreAdapter = GenreAdapter()
    private val castingAdapter = CastAdapter()
    private var title: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViews()
        setViewPager()
        getData(args.id.toInt())
        observeState()
        binding.imageButtonNavigate.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.imageButton3.setOnClickListener {
            shareMovie()
        }
    }

    private fun observeState() {
        viewModel.detailUiState.observe(viewLifecycleOwner) {
            when (it) {
                is DetailUiState.SuccessDetailMovie -> {
                    binding.detailItem = it.data
                    genreAdapter.differ.submitList(it.data.genres)
                    title = it.data.title.toString()
                }

                is DetailUiState.SuccessDetailCasting -> {
                    castingAdapter.differ.submitList(it.castData)
                    binding.detailLoading.gone()
                }

                is DetailUiState.Error -> {
                    requireActivity().showToast("Error", it.message, MotionToastStyle.ERROR)
                    binding.detailLoading.gone()
                }

                is DetailUiState.Loading -> {
                    binding.detailLoading.visible()
                }

            }
        }
    }

    private fun setViewPager() {
        with(binding) {
            viewPagerMovieDetail.adapter =
                ViewPagerAdapter(childFragmentManager, lifecycle, args.id.toInt())

            val tabsArray = arrayOf(
                "Trailers",
                "More Like This",
                "Comments",
            )

            TabLayoutMediator(tabLayoutMovieDetail, viewPagerMovieDetail) { tab, position ->
                tab.text = tabsArray[position]
            }.attach()

        }
    }

    private fun getData(id: Int) {
        viewModel.getMovieDetail(id)
        viewModel.getCastingDetail(id)
    }

    private fun setRecyclerViews() {
        binding.rvDetailGenre.adapter = genreAdapter
        binding.rvDetailCast.adapter = castingAdapter
    }

    private fun shareMovie() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, title)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(sendIntent, null))
    }
}