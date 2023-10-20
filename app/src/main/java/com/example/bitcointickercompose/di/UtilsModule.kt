package com.example.bitcointickercompose.di

import android.app.Application
import android.content.Context
import com.example.bitcointickercompose.domain.utils.StringResourceProvider
import com.example.bitcointickercompose.domain.utils.WorkerInterface
import com.example.bitcointickercompose.utils.StringResourceProviderImpl
import com.example.bitcointickercompose.utils.UserManagerDataStorage
import com.example.bitcointickercompose.utils.WorkerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideStringResourceProvider(application: Application): StringResourceProvider {
        return StringResourceProviderImpl(application)
    }

    @Provides
    @Singleton
    fun provideWorkerProvider(application: Application): WorkerInterface {
        return WorkerImpl(application)
    }

    @Singleton
    @Provides
    fun provideUserManager(@ApplicationContext context: Context): UserManagerDataStorage {
        return UserManagerDataStorage(context)
    }
}