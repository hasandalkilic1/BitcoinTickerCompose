package com.example.bitcointickercompose.domain.usecase.coins

import com.example.bitcointickercompose.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
    ) {

    operator fun invoke() = firebaseRepository.getCurrentUser()
}