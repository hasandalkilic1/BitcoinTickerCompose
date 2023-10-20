package com.example.bitcointickercompose.di

import com.example.bitcointickercompose.data.repository.CoinRepositoryImpl
import com.example.bitcointickercompose.data.source.local.CoinsRoomDB
import com.example.bitcointickercompose.domain.repository.CoinRepository
import com.example.bitcointickercompose.domain.source.LocalDataSource
import com.example.bitcointickercompose.domain.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCoinRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource,
        coinsRoomDB: CoinsRoomDB,
        @Named("Default") coroutinesContextDefault: CoroutineDispatcher
    ): CoinRepository =
        CoinRepositoryImpl(remoteDataSource, localDataSource, coinsRoomDB, coroutinesContextDefault)

    @Provides
    @Singleton
    @Named("IO")
    fun provideCoroutineContextIO(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @Named("Default")
    fun provideCoroutineContextDefault(): CoroutineDispatcher = Dispatchers.Default

}