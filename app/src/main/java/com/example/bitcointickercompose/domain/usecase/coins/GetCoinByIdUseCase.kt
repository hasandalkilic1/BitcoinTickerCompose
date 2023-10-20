package com.example.bitcointickercompose.domain.usecase.coins

import com.example.bitcointickercompose.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinByIdUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke(coinId: String) = coinRepository.coinById(coinId)
}