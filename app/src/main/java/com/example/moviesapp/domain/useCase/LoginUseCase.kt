package com.example.moviesapp.domain.useCase

import com.example.moviesapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(email: String, password: String) = authRepository.loginUser(email, password)

}