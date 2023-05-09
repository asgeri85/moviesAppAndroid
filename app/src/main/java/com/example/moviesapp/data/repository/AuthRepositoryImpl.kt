package com.example.moviesapp.data.repository

import com.example.moviesapp.common.NetworkResponseState
import com.example.moviesapp.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override fun loginUser(
        email: String,
        password: String
    ): Flow<NetworkResponseState<AuthResult>> = flow {
        emit(NetworkResponseState.Loading)
        val loginUser = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        emit(NetworkResponseState.Success(loginUser))
    }.catch {
        emit(NetworkResponseState.Error(it as Exception))
    }

    override fun registerUser(
        email: String,
        password: String
    ): Flow<NetworkResponseState<AuthResult>> = flow {
        emit(NetworkResponseState.Loading)
        val registerUser = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        emit(NetworkResponseState.Success(registerUser))
    }.catch {
        emit(NetworkResponseState.Error(it as Exception))
    }

    override fun getUserInfo(): Flow<NetworkResponseState<FirebaseUser>> = flow {
        emit(NetworkResponseState.Loading)
        firebaseAuth.currentUser?.let {
            emit(NetworkResponseState.Success(it))
        }
    }.catch {
        emit(NetworkResponseState.Error(it as Exception))
    }

    override fun signOut(): Flow<NetworkResponseState<Boolean>> = flow {
        emit(NetworkResponseState.Loading)
        firebaseAuth.signOut()
        emit(NetworkResponseState.Success(true))
    }.catch { NetworkResponseState.Error(it as Exception) }
}