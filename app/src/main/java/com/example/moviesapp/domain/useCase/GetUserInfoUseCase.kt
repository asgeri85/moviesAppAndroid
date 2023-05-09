package com.example.moviesapp.domain.useCase

import com.example.moviesapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() = authRepository.getUserInfo()

}