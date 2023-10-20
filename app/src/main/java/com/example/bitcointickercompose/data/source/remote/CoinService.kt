package com.example.bitcointickercompose.data.source.remote

import com.example.bitcointickercompose.data.model.coindetails.CoinDetailResponse
import com.example.bitcointickercompose.data.model.coinlist.CoinListResponse
import com.example.bitcointickercompose.data.model.coinmarket.CoinMarketResponse
import com.example.bitcointickercompose.utils.Constants.COIN_BY_ID
import com.example.bitcointickercompose.utils.Constants.COIN_LIST
import com.example.bitcointickercompose.utils.Constants.COIN_MARKETS
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinService {

    @GET(COIN_LIST)
    suspend fun getCoinList(): List<CoinListResponse>

    @GET(COIN_MARKETS)
    suspend fun getCoinMarkets(): List<CoinMarketResponse>

    @GET(COIN_BY_ID)
    suspend fun getCoinById(@Path("id") coinId: String): CoinDetailResponse
}