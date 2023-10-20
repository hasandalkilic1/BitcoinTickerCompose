package com.example.bitcointickercompose.domain.usecase

import com.example.bitcointickercompose.domain.repository.FirebaseRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    operator fun invoke(email: String, password: String) =
        firebaseRepository.signInWithEmailAndPassword(email, password)
}