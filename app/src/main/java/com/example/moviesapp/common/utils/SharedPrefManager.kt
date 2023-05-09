package com.example.moviesapp.common.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.moviesapp.common.utils.Constants.SP_NAME
import com.example.moviesapp.common.utils.Constants.TOKEN_USER

class SharedPrefManager(private val context: Context) {

    private val masterKey: MasterKey by lazy {
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    }

    private val sharedPreferences: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context,
            SP_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveToken(token: String?) {
        with(sharedPreferences.edit()) {
            putString(TOKEN_USER, token)
            apply()
        }
    }

    fun getToken() = sharedPreferences.getString(TOKEN_USER, "")

}