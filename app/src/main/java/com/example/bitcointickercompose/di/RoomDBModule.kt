package com.example.bitcointickercompose.di

import android.content.Context
import androidx.room.Room
import com.example.bitcointickercompose.data.source.local.CoinsDAO
import com.example.bitcointickercompose.data.source.local.CoinsRoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Provides
    @Singleton
    fun provideFavoritesRoomDB(@ApplicationContext appContext: Context): CoinsRoomDB =
        Room.databaseBuilder(appContext, CoinsRoomDB::class.java, "coins.db").build()


    @Provides
    @Singleton
    fun provideCoinsDAO(coinsRoomDB: CoinsRoomDB): CoinsDAO = coinsRoomDB.coinsDAO()
}