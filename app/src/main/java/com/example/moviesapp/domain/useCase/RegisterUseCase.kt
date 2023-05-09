package com.example.moviesapp.domain.useCase

import com.example.moviesapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(email: String, password: String) =
        authRepository.registerUser(email, password)

}