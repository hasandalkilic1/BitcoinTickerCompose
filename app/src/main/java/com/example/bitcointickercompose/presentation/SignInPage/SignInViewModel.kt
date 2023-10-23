package com.example.bitcointickercompose.presentation.SignInPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointickercompose.domain.usecase.SignInUseCase
import com.example.bitcointickercompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase) : ViewModel() {

    private val _signInState = mutableStateOf<SignInState>(SignInState())
    val signInState: State<SignInState> = _signInState

    fun signIn(email: String, password: String) = viewModelScope.launch {
        signInUseCase.invoke(email, password).collect {
            when (it) {
                is Resource.Error -> {
                    _signInState.value = SignInState(error = "SignInError")
                }

                is Resource.Loading -> {
                    _signInState.value = SignInState(isLoading = true)
                }

                is Resource.Success -> {
                    _signInState.value = SignInState(authResult = it.data)
                }
            }
        }
    }
}
