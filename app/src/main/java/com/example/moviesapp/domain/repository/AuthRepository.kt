package com.example.moviesapp.domain.repository

import com.example.moviesapp.common.NetworkResponseState
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun loginUser(email: String, password: String): Flow<NetworkResponseState<AuthResult>>

    fun registerUser(email: String, password: String): Flow<NetworkResponseState<AuthResult>>

    fun getUserInfo(): Flow<NetworkResponseState<FirebaseUser>>

    fun signOut(): Flow<NetworkResponseState<Boolean>>

}
