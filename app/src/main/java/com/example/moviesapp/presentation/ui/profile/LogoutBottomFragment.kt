package com.example.moviesapp.presentation.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.showToast
import com.example.moviesapp.databinding.FragmentLogoutBottomBinding
import com.example.moviesapp.presentation.ui.auth.AuthUiState
import com.example.moviesapp.presentation.ui.auth.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class LogoutBottomFragment : BottomSheetDialogFragment(R.layout.fragment_logout_bottom) {

    private val binding: FragmentLogoutBottomBinding by viewBinding(FragmentLogoutBottomBinding::bind)
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()

        binding.buttonOk.setOnClickListener {
            viewModel.logout()
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeState() {
        viewModel.authResult.observe(viewLifecycleOwner) {
            when (it) {
                is AuthUiState.Success -> {
                    requireActivity().showToast(
                        "Success",
                        "Your account has been logged out",
                        MotionToastStyle.SUCCESS
                    )
                    findNavController().navigate(LogoutBottomFragmentDirections.actionLogoutBottomFragmentToLoginFragment())
                }

                is AuthUiState.Error -> requireActivity().showToast(
                    "Error",
                    it.message,
                    MotionToastStyle.ERROR
                )

                is AuthUiState.Loading -> {

                }
            }
        }
    }

}