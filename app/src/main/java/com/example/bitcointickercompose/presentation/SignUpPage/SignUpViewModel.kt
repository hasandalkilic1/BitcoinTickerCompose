package com.example.bitcointickercompose.presentation.SignUpPage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcointickercompose.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _signUpState = mutableStateOf<SignUpState>(SignUpState())
    val signUpState: State<SignUpState> = _signUpState

    fun signUp(email: String, password: String) = viewModelScope.launch {
        signUpUseCase.invoke(email, password).collect {
            _signUpState.value = SignUpState(authResult = it.data)
        }
    }
}