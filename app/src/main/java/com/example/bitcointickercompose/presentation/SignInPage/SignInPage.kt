package com.example.bitcointickercompose.presentation.SignInPage


import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bitcointickercompose.R
import com.example.bitcointickercompose.presentation.Screen
import com.example.bitcointickercompose.presentation.theme.light_Green
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

@Composable
fun SignInPage(
    navController: NavController,
    viewModel: SignInViewModel = hiltViewModel(),
    context: Context
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val signInState = viewModel.signInState.value

    LaunchedEffect(signInState) {
        signInState.let { state ->
            when {
                state.authResult != null -> {
                    Toast.makeText(
                        context,
                        "Başarılı Giriş!",
                        Toast.LENGTH_LONG
                    ).show()

                    val bundle = Bundle()
                    bundle.putString(FirebaseAnalytics.Param.METHOD, "CUSTOM")
                    Firebase.analytics.logEvent(
                        FirebaseAnalytics.Event.LOGIN,
                        bundle
                    )
                    navController.navigate(Screen.HomePage.route)
                }

                state.error == "signInError" -> {
                    Toast.makeText(
                        context,
                        "Error! {${state.error}}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(light_Green),
        contentAlignment = Alignment.Center,

        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (signInState.isLoading) {
                CircularProgressIndicator()
            }
            Image(
                painter = painterResource(id = R.drawable.sign_in_picture),
                contentDescription = "sign in picture",
                modifier = Modifier
                    .size(250.dp, 250.dp)
                    .padding(top = 16.dp),
                alignment = Alignment.Center,
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .align(CenterHorizontally),
                shape = RoundedCornerShape(32.dp),
                border = BorderStroke(1.dp, Color.White),
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { if (it.length <= 30) email = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        placeholder = { Text(text = "Email") },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = light_Green,
                        ),
                        shape = RoundedCornerShape(32.dp),
                        maxLines = 1,
                        singleLine = true,
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { if (it.length <= 30) password = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        placeholder = { Text(text = "Password") },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = light_Green,
                        ),
                        shape = RoundedCornerShape(32.dp),
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                    )

                    Button(
                        modifier = Modifier.bounceClick(),
                        onClick = {
                            viewModel.signIn(email, password)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = light_Green, contentColor = Color.Black
                        ),
                    ) {
                        Text(text = "Sign In")
                    }
                }
            }
        }
    }
}