package com.example.bitcointickercompose.di

import com.example.bitcointickercompose.data.source.local.CoinsDAO
import com.example.bitcointickercompose.data.source.local.RoomDataSourceImpl
import com.example.bitcointickercompose.data.source.remote.CoinService
import com.example.bitcointickercompose.data.source.remote.RemoteDataSourceImpl
import com.example.bitcointickercompose.domain.source.LocalDataSource
import com.example.bitcointickercompose.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(coinService: CoinService): RemoteDataSource =
        RemoteDataSourceImpl(coinService = coinService)

    @Provides
    @Singleton
    fun provideLocalDataSource(coinsDAO: CoinsDAO): LocalDataSource =
        RoomDataSourceImpl(coinsDAO = coinsDAO)
}