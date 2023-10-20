package com.example.bitcointickercompose.presentation.HomePage

import com.example.bitcointickercompose.domain.model.CoinDetail
import com.example.bitcointickercompose.domain.model.CoinMarkets

data class CoinMarketsState(
    val isLoading:Boolean = false,
    val coinMarkets: List<CoinMarkets> = emptyList(),
    val error: String = ""
)