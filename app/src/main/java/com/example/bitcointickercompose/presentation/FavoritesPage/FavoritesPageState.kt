package com.example.bitcointickercompose.presentation.FavoritesPage

import com.example.bitcointickercompose.domain.model.Favorites

data class FavoritesPageState(
    val isLoading:Boolean = false,
    var favorites: List<Favorites> = emptyList(),
    val error: String = ""
)