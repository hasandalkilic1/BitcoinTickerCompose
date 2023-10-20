package com.example.bitcointickercompose.domain.usecase.coins

import com.example.bitcointickercompose.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {
    operator fun invoke() = coinRepository.coinList()
}