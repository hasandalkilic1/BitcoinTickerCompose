package com.example.bitcointickercompose.presentation.FavoritesPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointickercompose.R
import com.example.bitcointickercompose.domain.model.Favorites
import com.example.bitcointickercompose.domain.usecase.favourite.DeleteFromFavoritesUseCase
import com.example.bitcointickercompose.domain.usecase.favourite.GetFavoritesUseCase
import com.example.bitcointickercompose.domain.utils.StringResourceProvider
import com.example.bitcointickercompose.utils.Resource
import com.example.bitcointickercompose.utils.UIScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val deleteFromFavoritesUseCase: DeleteFromFavoritesUseCase,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {

    private val _deleteFromFavoritesFlow = MutableSharedFlow<UIScreen>()
    val deleteFromFavoritesFlow = _deleteFromFavoritesFlow.asSharedFlow()

    private val _favoritesState = mutableStateOf<FavoritesPageState>(FavoritesPageState())
    val favoritesState: State<FavoritesPageState> = _favoritesState

    init {
        getFavorites()
    }

    fun getFavorites() = viewModelScope.launch {
        getFavoritesUseCase.invoke().collect {
            when (it) {
                is Resource.Error -> {
                    _favoritesState.value = FavoritesPageState(error = it.message ?: "Error")
                }
                is Resource.Loading -> {
                    _favoritesState.value = FavoritesPageState(isLoading = true)
                }
                is Resource.Success -> {
                    _favoritesState.value = FavoritesPageState(favorites = it.data ?: emptyList())
                }
            }
        }
    }

    fun deleteFromFavorites(favoritesUI: Favorites) = viewModelScope.launch {
        deleteFromFavoritesUseCase.invoke(favoritesUI).collect {
            when (it) {
                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    _deleteFromFavoritesFlow.emit(
                        UIScreen.SnackBar(
                            stringResourceProvider.getString(
                                R.string.delete_from_favourite
                            )
                        )
                    )
                }

                is Resource.Error -> {
                    _deleteFromFavoritesFlow.emit(UIScreen.SnackBar(it.message.toString()))
                }
            }
        }
    }
}