package com.example.bitcointickercompose.domain.usecase

import com.example.bitcointickercompose.domain.utils.WorkerInterface
import javax.inject.Inject

class WorkerUseCase @Inject constructor(
    private val workerProvider: WorkerInterface
) {
    operator fun invoke() = workerProvider.onSuccess()

}