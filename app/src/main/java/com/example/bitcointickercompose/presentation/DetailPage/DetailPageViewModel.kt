package com.example.bitcointickercompose.presentation.DetailPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointickercompose.R
import com.example.bitcointickercompose.domain.model.CoinDetail
import com.example.bitcointickercompose.domain.model.Favorites
import com.example.bitcointickercompose.domain.usecase.coins.GetCoinByIdUseCase
import com.example.bitcointickercompose.domain.usecase.coins.GetCurrentPriceByIdUseCase
import com.example.bitcointickercompose.domain.usecase.favourite.AddToFavoritesUseCase
import com.example.bitcointickercompose.domain.utils.StringResourceProvider
import com.example.bitcointickercompose.utils.Constants.COIN_ID
import com.example.bitcointickercompose.utils.Resource
import com.example.bitcointickercompose.utils.UIScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPageViewModel @Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val currentPriceByIdUseCase: GetCurrentPriceByIdUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val stateHandle: SavedStateHandle,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {

    private val _coinDetail = mutableStateOf<DetailPageState>(DetailPageState())
    val coinDetail: State<DetailPageState> = _coinDetail

    private val _currentPrice = mutableDoubleStateOf(0.0)
    val currentPrice: State<Double> = _currentPrice

    private val _addToFavourite = MutableSharedFlow<UIScreen>()
    val addToFavourite = _addToFavourite.asSharedFlow()

    init {
        stateHandle.get<String>(COIN_ID)?.let {
            coinById(it)
        }
    }

    private fun coinById(coinId: String) = viewModelScope.launch {
        getCoinByIdUseCase.invoke(coinId).collect {
            when (it) {
                is Resource.Success -> {
                    _coinDetail.value = DetailPageState(coinDetail = it.data)
                }
                is Resource.Loading -> {
                    _coinDetail.value = DetailPageState(isLoading = true)
                }
                is Resource.Error -> {
                    _coinDetail.value = DetailPageState(error = it.message ?: "Error!")
                }
            }
        }
    }

    fun currentPriceById(period: kotlin.time.Duration) = viewModelScope.launch {
        stateHandle.get<String>(COIN_ID)?.let {
            currentPriceByIdUseCase.invoke(period,it).collect {
                when (it) {
                    is Resource.Success -> {
                        _currentPrice.doubleValue = it.data ?: 0.0
                    }
                    is Resource.Loading -> {}
                    is Resource.Error -> {}
                }
            }
        }
    }

    fun addToFavorites(coin: CoinDetail) = viewModelScope.launch {
        coin.let {
            addToFavoritesUseCase.invoke(it).collect {
                when (it) {
                    is Resource.Success -> {
                        _addToFavourite.emit(UIScreen.SnackBar(stringResourceProvider.getString(R.string.add_to_favorites_successful)))
                    }
                    is Resource.Loading -> {}
                    is Resource.Error -> _addToFavourite.emit(
                        UIScreen.SnackBar(
                            it.message.toString()
                        )
                    )
                }
            }
        } ?: run {
            _addToFavourite.emit(UIScreen.SnackBar(stringResourceProvider.getString(R.string.something_went_wrong)))
        }
    }
}