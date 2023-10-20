package com.example.bitcointickercompose.domain.model

data class CoinMarkets(
    val id: Int?,
    val coinId: String?,
    val currentPrice: Double?,
    val image: String?,
    val name: String?,
    val priceChangePercentage24h: Double?
)