package com.example.bitcointickercompose.utils

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.bitcointickercompose.domain.utils.WorkerInterface
import com.example.bitcointickercompose.utils.Constants.SYNC_DATA
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerImpl @Inject constructor(context: Context) : WorkerInterface {

    private val workManager = WorkManager.getInstance(context)

    private val workConstraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true)
        .build()

    override fun createWork() {

        val workRequest = PeriodicWorkRequestBuilder<CoinWorker>(
            15, TimeUnit.MINUTES,
            15, TimeUnit.MINUTES
        ).setConstraints(workConstraints).setInitialDelay(15, TimeUnit.MINUTES).addTag(SYNC_DATA)
            .build()

        workManager.enqueueUniquePeriodicWork(
            Constants.SYNC_DATA_WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    override fun onSuccess() = workManager.getWorkInfosByTagLiveData(SYNC_DATA)
}