package com.example.bitcointickercompose.presentation.SearchPage

import com.example.bitcointickercompose.domain.model.CoinList

data class CoinListState(
    val isLoading: Boolean = false,
    val coinList: List<CoinList> = emptyList(),
    val error: String = ""
)