package com.example.bitcointickercompose.domain.usecase.favourite

import com.example.bitcointickercompose.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    operator fun invoke() = firebaseRepository.getFavourites()
}