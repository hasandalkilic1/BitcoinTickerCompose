package com.example.bitcointickercompose.domain.usecase.favourite

import com.example.bitcointickercompose.domain.model.Favorites
import com.example.bitcointickercompose.domain.repository.FirebaseRepository
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {
    operator fun invoke(favouritesUI: Favorites) =
        firebaseRepository.deleteFromFavourites(favouritesUI)
}