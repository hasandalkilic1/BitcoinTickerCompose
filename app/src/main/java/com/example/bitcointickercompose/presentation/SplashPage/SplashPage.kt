package com.example.bitcointickercompose.presentation.SplashPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bitcointickercompose.R
import com.example.bitcointickercompose.presentation.Screen
import com.example.bitcointickercompose.presentation.theme.light_Green
import kotlinx.coroutines.delay

@Composable
fun SplashPage(navController: NavController) {

    var isPlaying by remember {
        mutableStateOf(true)
    }

    var speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.animation_splash)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false
    )

    LaunchedEffect(Unit) {
        delay(5000)
        navController.navigate(Screen.SignInPage.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(light_Green),
        contentAlignment = Alignment.Center,
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center
        )
    }
}
