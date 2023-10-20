package com.example.bitcointickercompose.domain.repository

import com.example.bitcointickercompose.domain.model.CoinDetail
import com.example.bitcointickercompose.domain.model.CoinList
import com.example.bitcointickercompose.domain.model.CoinMarkets
import com.example.bitcointickercompose.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

interface CoinRepository {

    fun coinsMarkets(): Flow<Resource<List<CoinMarkets>>>

    fun coinList(): Flow<Resource<List<CoinList>>>

    fun searchCoin(searchQuery: String): Flow<Resource<List<CoinList>>>

    fun coinById(coinId: String): Flow<Resource<CoinDetail>>

    fun currentPriceById(period: Duration, coinId: String): Flow<Resource<Double>>
}