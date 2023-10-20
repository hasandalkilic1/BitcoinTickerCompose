package com.example.bitcointickercompose.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bitcointickercompose.data.model.coinlist.CoinListEntity
import com.example.bitcointickercompose.data.model.coinmarket.CoinMarketEntity

@Database(
    entities = [CoinListEntity::class, CoinMarketEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CoinsRoomDB : RoomDatabase() {
    abstract fun coinsDAO(): CoinsDAO
}