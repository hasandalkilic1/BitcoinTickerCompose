package com.example.bitcointickercompose.presentation.SearchPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointickercompose.domain.usecase.coins.GetCoinListUseCase
import com.example.bitcointickercompose.domain.usecase.coins.SearchCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchPageViewModel @Inject constructor(
    getCoinListUseCase: GetCoinListUseCase,
    private val searchCoinUseCase: SearchCoinUseCase
) : ViewModel() {

    private val _coinListState = mutableStateOf<CoinListState>(CoinListState())
    val coinListState: State<CoinListState> = _coinListState

    val coinList = getCoinListUseCase.invoke()

    fun searchCoin(searchQuery: String) = viewModelScope.launch {
        searchCoinUseCase.invoke(searchQuery).collect{
            _coinListState.value = CoinListState(coinList = it.data ?: emptyList())
        }
    }
}