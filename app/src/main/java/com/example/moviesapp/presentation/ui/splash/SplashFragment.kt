package com.example.moviesapp.presentation.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.common.delegate.viewBinding
import com.example.moviesapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>()
    private var auth: Boolean = false
    private var job: Job? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        navigateScreen()
    }

    private fun observeData() {
        viewModel.userAuth.observe(viewLifecycleOwner) {
            auth = it
        }
    }

    private fun navigateScreen() {
        job = coroutineScope.launch {
            delay(2500)
            if (auth) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment2())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

}