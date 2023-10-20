package com.example.bitcointickercompose.presentation.DetailPage

import com.example.bitcointickercompose.domain.model.CoinDetail

data class DetailPageState(
    val isLoading:Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)