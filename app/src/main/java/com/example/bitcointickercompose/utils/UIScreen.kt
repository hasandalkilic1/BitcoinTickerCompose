package com.example.bitcointickercompose.utils

sealed interface UIScreen {
    data class SnackBar(val message: String) : UIScreen
}