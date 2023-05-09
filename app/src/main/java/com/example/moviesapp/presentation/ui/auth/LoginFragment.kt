package com.example.moviesapp.presentation.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.common.utils.gone
import com.example.moviesapp.common.utils.showToast
import com.example.moviesapp.common.utils.visible
import com.example.moviesapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.buttonLogin.setOnClickListener {
            login()
        }
        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

    private fun observeData() {
        viewModel.authResult.observe(viewLifecycleOwner) {
            when (it) {
                is AuthUiState.Success -> {
                    binding.loginLoading.gone()
                    requireActivity().showToast(
                        "Successful",
                        "Account successfully logged in",
                        MotionToastStyle.SUCCESS
                    )
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }

                is AuthUiState.Loading -> {
                    binding.loginLoading.visible()
                }

                is AuthUiState.Error -> {
                    binding.loginLoading.gone()
                    requireActivity().showToast(
                        "Error",
                        it.message,
                        MotionToastStyle.ERROR
                    )
                }
            }
        }
    }

    private fun login() {
        val email = binding.editLoginEmail.text.toString().trim()
        val password = binding.editLoginPassword.text.toString().trim()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.login(email, password)
        } else {
            requireActivity().showToast(
                "Warning",
                "Please fill in all fields",
                MotionToastStyle.WARNING
            )
        }
    }

}