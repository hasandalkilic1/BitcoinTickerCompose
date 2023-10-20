package com.example.bitcointickercompose.domain.usecase

import com.example.bitcointickercompose.domain.repository.FirebaseRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    operator fun invoke() = firebaseRepository.signOut()
}