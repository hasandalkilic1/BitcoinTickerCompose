package com.example.bitcointickercompose.domain.source

import com.example.bitcointickercompose.data.model.coindetails.CoinDetailResponse
import com.example.bitcointickercompose.data.model.coinlist.CoinListResponse
import com.example.bitcointickercompose.data.model.coinmarket.CoinMarketResponse

interface RemoteDataSource {
    suspend fun getCoinList(): List<CoinListResponse>

    suspend fun getCoinMarkets(): List<CoinMarketResponse>

    suspend fun getCoinById(coinId: String): CoinDetailResponse
}