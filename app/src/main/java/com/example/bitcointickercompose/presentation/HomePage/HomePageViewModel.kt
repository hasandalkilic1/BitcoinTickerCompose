package com.example.bitcointickercompose.presentation.HomePage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointickercompose.domain.usecase.SignOutUseCase
import com.example.bitcointickercompose.domain.usecase.WorkerUseCase
import com.example.bitcointickercompose.domain.usecase.coins.GetCoinMarketUseCase
import com.example.bitcointickercompose.domain.usecase.coins.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val getCoinMarketUseCase: GetCoinMarketUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getUserUseCase: GetUserUseCase,
    workerUseCase: WorkerUseCase
): ViewModel() {

    private val _coinMarketsState = mutableStateOf<CoinMarketsState>(CoinMarketsState())
    val coinMarketsState: State<CoinMarketsState> = _coinMarketsState

    init {
        coinMarkets()
    }

    private fun coinMarkets() = viewModelScope.launch {
        getCoinMarketUseCase.invoke().collect() {
            _coinMarketsState.value = CoinMarketsState(coinMarkets = it.data ?: emptyList())
        }
    }

    fun signOut() = signOutUseCase.invoke()

    val currentUser = getUserUseCase.invoke()

    val workInfo = workerUseCase.invoke()
}