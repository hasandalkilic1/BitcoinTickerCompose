package com.example.bitcointickercompose.presentation.SignInPage

import com.google.firebase.auth.AuthResult

data class SignInState (
    val isLoading: Boolean = false,
    val authResult: AuthResult? = null,
    val error: String = ""
)