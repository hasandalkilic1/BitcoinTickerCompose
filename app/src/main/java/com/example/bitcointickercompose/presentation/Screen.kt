package com.example.bitcointickercompose.presentation

sealed class Screen(val route: String) {
    object SplashPage : Screen("splash_page")
    object SignInPage : Screen("signIn_page")
}