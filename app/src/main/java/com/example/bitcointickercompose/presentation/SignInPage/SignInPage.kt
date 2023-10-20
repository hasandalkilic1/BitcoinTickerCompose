package com.example.bitcointickercompose.presentation.SignInPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bitcointickercompose.R
import com.example.bitcointickercompose.presentation.theme.lightGreen

@Composable
fun SignInPage(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGreen),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    placeholder = { Text(text = "Email") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = lightGreen,
                    ),
                    shape = RoundedCornerShape(32.dp),
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    placeholder = { Text(text = "Password") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = lightGreen,
                    ),
                    shape = RoundedCornerShape(32.dp),
                )
            }
        }
    }
}