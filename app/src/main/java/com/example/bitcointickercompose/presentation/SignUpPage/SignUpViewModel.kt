package com.example.bitcointickercompose.presentation.SignUpPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointickercompose.domain.usecase.SignUpUseCase
import com.example.bitcointickercompose.presentation.SignInPage.SignInState
import com.example.bitcointickercompose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _signUpState = mutableStateOf<SignUpState>(SignUpState())
    val signUpState: State<SignUpState> = _signUpState

    fun signUp(email: String, password: String) = viewModelScope.launch {
        signUpUseCase.invoke(email, password).collect {
            when (it) {
                is Resource.Error -> {
                    _signUpState.value = SignUpState(error = "SignUpError")
                }

                is Resource.Loading -> {
                    _signUpState.value = SignUpState(isLoading = true)
                }

                is Resource.Success -> {
                    _signUpState.value = SignUpState(authResult = it.data)
                }
            }
        }
    }
}