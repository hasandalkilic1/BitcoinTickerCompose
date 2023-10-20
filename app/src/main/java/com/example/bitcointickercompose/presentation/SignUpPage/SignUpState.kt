package com.example.bitcointickercompose.presentation.SignUpPage

import com.google.firebase.auth.AuthResult

data class SignUpState(
    val isLoading: Boolean = false,
    val authResult: AuthResult? = null,
    val error: String = ""
)