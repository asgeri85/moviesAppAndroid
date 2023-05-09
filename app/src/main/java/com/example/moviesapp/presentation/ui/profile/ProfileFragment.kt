package com.example.moviesapp.presentation.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.showToast
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel by viewModels<ProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
        binding.imageViewLogout.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLogoutBottomFragment())
        }
    }

    private fun observeState() {
        viewModel.userInfo.observe(viewLifecycleOwner) {
            when (it) {
                is ProfileUiState.Success -> {
                    binding.profileLoading.gone()
                    it.user.email?.let { email ->
                        binding.textViewUserMail.text = email
                    }
                }

                is ProfileUiState.Error -> {
                    binding.profileLoading.gone()
                    requireActivity().showToast("Error", it.message, MotionToastStyle.ERROR)
                }

                is ProfileUiState.Loading -> {
                    binding.profileLoading.visible()
                }
            }
        }
    }

}