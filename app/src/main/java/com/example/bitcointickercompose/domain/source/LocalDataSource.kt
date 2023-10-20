package com.example.bitcointickercompose.domain.source

import com.example.bitcointickercompose.data.model.coinlist.CoinListEntity
import com.example.bitcointickercompose.data.model.coinmarket.CoinMarketEntity

interface LocalDataSource {
    suspend fun insertCoinList(items: List<CoinListEntity>)

    suspend fun insertCoinMarketsList(items: List<CoinMarketEntity>)

    suspend fun getCoinList(): List<CoinListEntity>

    suspend fun getCoinMarkets(): List<CoinMarketEntity>

    suspend fun searchCoin(searchQuery: String): List<CoinListEntity>

    suspend fun deleteCoinList()

    suspend fun deleteCoinMarkets()
}