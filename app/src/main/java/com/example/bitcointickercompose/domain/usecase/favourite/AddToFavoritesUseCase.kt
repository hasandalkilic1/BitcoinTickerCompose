package com.example.bitcointickercompose.domain.usecase.favourite

import com.example.bitcointickercompose.domain.model.CoinDetail
import com.example.bitcointickercompose.domain.repository.FirebaseRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    operator fun invoke(coinDetail: CoinDetail) = firebaseRepository.addToFavourites(coinDetail)
}