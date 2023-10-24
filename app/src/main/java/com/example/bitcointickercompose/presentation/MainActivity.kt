package com.example.bitcointickercompose.presentation

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bitcointickercompose.presentation.HomePage.HomePage
import com.example.bitcointickercompose.presentation.SignInPage.SignInPage
import com.example.bitcointickercompose.presentation.SignUpPage.SignUpPage
import com.example.bitcointickercompose.presentation.SplashPage.SplashPage
import com.example.bitcointickercompose.presentation.theme.BitcoinTickerComposeTheme1
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var backButtonPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitcoinTickerComposeTheme1 {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashPage.route
                    ) {
                        composable(route = Screen.SplashPage.route) {
                            SplashPage(navController = navController)
                        }
                        composable(route = Screen.SignInPage.route){
                            SignInPage(navController = navController)
                        }
                        composable(route = Screen.HomePage.route){
                            HomePage(navController = navController)
                        }
                        composable(route = Screen.SignUpPage.route) {
                            SignUpPage(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

