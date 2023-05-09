package com.example.moviesapp.presentation.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.showToast
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding(FragmentRegisterBinding::bind)
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.buttonRegister.setOnClickListener {
            registerUser()
        }
        binding.textViewSignIn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeData() {
        viewModel.authResult.observe(viewLifecycleOwner) {
            when (it) {
                is AuthUiState.Success -> {
                    binding.registerLoading.gone()
                    requireActivity().showToast(
                        "Successful",
                        "Your account has been successfully created",
                        MotionToastStyle.SUCCESS
                    )
                    findNavController().popBackStack()
                }

                is AuthUiState.Error -> {
                    binding.registerLoading.gone()
                    requireActivity().showToast(
                        "Error",
                        it.message,
                        MotionToastStyle.ERROR
                    )
                }

                is AuthUiState.Loading -> binding.registerLoading.visible()
            }
        }
    }

    private fun registerUser() {
        val email = binding.editRegisterEmail.text.toString().trim()
        val password = binding.editRegisterPassword.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.register(email, password)
        } else {
            requireActivity().showToast(
                "Warning",
                "Please fill in all fields",
                MotionToastStyle.WARNING
            )
        }
    }
}