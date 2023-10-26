package com.example.bitcointickercompose.presentation.DetailPage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DetailPage(navController: NavController, viewModel: DetailPageViewModel = hiltViewModel()) {
    Text(text = "DENEME")
}