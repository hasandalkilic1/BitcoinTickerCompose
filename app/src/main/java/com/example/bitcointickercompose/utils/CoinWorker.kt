package com.example.bitcointickercompose.utils

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.bitcointickercompose.domain.repository.FirebaseRepository
import com.example.bitcointickercompose.domain.source.RemoteDataSource
import com.example.bitcointickercompose.utils.Constants.DESCRIPTION
import com.example.bitcointickercompose.utils.Constants.TITLE
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CoinWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val authRepository: FirebaseRepository,
    private val remoteDataSource: RemoteDataSource
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {

            var equalState = true
            val coinsMarkets = remoteDataSource.getCoinMarkets()
            authRepository.getFavourites().collect { result ->
                when (result) {
                    is Resource.Success -> {

                        if (result.data!!.isNotEmpty()) {
                            result.data.forEach { favourite ->
                                coinsMarkets.forEach { coinMarkets ->
                                    if (favourite.name == coinMarkets.name && favourite.currentPrice != coinMarkets.currentPrice) {
                                        equalState = !equalState
                                    }
                                }
                            }

                            if (equalState) Notification.showNotification(
                                applicationContext,
                                TITLE,
                                DESCRIPTION
                            )
                        }
                    }
                    else -> {}
                }
            }
            Result.success()
        } catch (exception: Exception) {
            Result.failure()
        }
    }
}