package com.example.moviesapp.presentation.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.common.utils.SharedPrefManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sp: SharedPrefManager
) : ViewModel() {

    private val _userAuth = MutableLiveData<Boolean>(false)
    val userAuth: LiveData<Boolean> get() = _userAuth

    init {
        getUserAuth()
    }

    private fun getUserAuth() {
        sp.getToken()?.let {
            if (it != "") {
                _userAuth.value = true
            }
        }
    }
}