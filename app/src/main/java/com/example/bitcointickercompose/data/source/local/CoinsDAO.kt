package com.example.bitcointickercompose.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bitcointickercompose.data.model.coinlist.CoinListEntity
import com.example.bitcointickercompose.data.model.coinmarket.CoinMarketEntity

@Dao
interface CoinsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinList(items: List<CoinListEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinMarkets(items: List<CoinMarketEntity>)

    @Query("SELECT * FROM coin_list ORDER BY id ASC")
    fun getCoinList(): List<CoinListEntity>

    @Query("SELECT * FROM coin_markets ORDER BY id ASC")
    fun getCoinMarkets(): List<CoinMarketEntity>

    @Query("SELECT * FROM coin_list WHERE name LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    fun searchCoin(searchQuery: String): List<CoinListEntity>

    @Query("DELETE FROM coin_list")
    fun deleteCoinList()

    @Query("DELETE FROM coin_markets")
    fun deleteCoinMarkets()
}