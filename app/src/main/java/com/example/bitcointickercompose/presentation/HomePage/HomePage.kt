package com.example.bitcointickercompose.presentation.HomePage

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bitcointickercompose.R
import com.example.bitcointickercompose.domain.model.CoinMarkets
import com.example.bitcointickercompose.presentation.MainActivity
import com.example.bitcointickercompose.presentation.Screen
import com.example.bitcointickercompose.presentation.theme.beige
import com.example.bitcointickercompose.presentation.theme.light_Green
import com.example.bitcointickercompose.utils.Resource
import com.example.bitcointickercompose.utils.UserManagerDataStorage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomePageViewModel = hiltViewModel(),
    userManager: UserManagerDataStorage
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(light_Green),
    ) {
        Scaffold(
            topBar = { HomePageTopBar(userManager = userManager) },
            modifier = Modifier
                .height(64.dp)
                .clip(
                    RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .background(beige)
                .fillMaxWidth()
        ) {

        }

        HomePageCoinMarketsColumn(navController = navController)

        Scaffold(
            bottomBar = { HomePageBottomButtons() },
            modifier = Modifier
                .height(64.dp)
                .clip(
                    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .align(Alignment.BottomCenter)
                .background(beige)
        ) {

        }
    }
}

@Composable
fun HomePageCoinMarketsColumn(
    navController: NavController,
    viewModel: HomePageViewModel = hiltViewModel(),
) {
    val coinMarketsState = viewModel.coinMarketsState.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ) {
        items(coinMarketsState.coinMarkets) { coin ->
            CoinMarketsRawItem(
                coin = coin,
                onItemClick = { navController.navigate(Screen.DetailPage.route) }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White)
            )
        }
    }
}

@Composable
fun CoinMarketsRawItem(coin: CoinMarkets, onItemClick: (CoinMarkets) -> Unit) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable { onItemClick(coin) }
    ) {

        val (coinName, price, coinImage, priceChange, coinChangeImage) = createRefs()

        Image(
            painter = rememberImagePainter(data = coin.image),
            contentDescription = "Coin Image",
            modifier = Modifier
                .size(30.dp)
                .constrainAs(coinImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
        )

        Text(
            text = coin.name ?: "Unknown",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(coinName) {
                    top.linkTo(parent.top)
                    start.linkTo(coinImage.end)
                }
                .padding(start = 12.dp)
        )

        Text(
            text = "$${coin.currentPrice}",
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(price) {
                    top.linkTo(coinName.bottom)
                    start.linkTo(coinImage.end)
                }
                .padding(start = 12.dp)
        )

        Text(
            text = "${coin.priceChangePercentage24h}%",
            color = Color.Black,
            fontSize = 12.sp,
            modifier = Modifier
                .constrainAs(priceChange) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(end = 12.dp)
        )

        Image(
            painter = painterResource(
                id = if (coin.priceChangePercentage24h!! > 0) {
                    R.drawable.increase
                } else {
                    R.drawable.decrease
                }
            ),
            contentDescription = "Coin Change Image",
            modifier = Modifier
                .size(20.dp, 14.dp)
                .constrainAs(coinChangeImage) {
                    top.linkTo(priceChange.top)
                    start.linkTo(priceChange.end)
                    bottom.linkTo(priceChange.bottom)
                },
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun HomePageBottomButtons() {
    val isHomeButtonClicked = remember {
        mutableStateOf(true)
    }

    val isFavoritesButtonClicked = remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                isHomeButtonClicked.value = true
                isFavoritesButtonClicked.value = false
            },
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clip(RoundedCornerShape(topStart = 16.dp)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = beige)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_home_24),
                    contentDescription = "home button"
                )
                if (isHomeButtonClicked.value) {
                    Text(text = "Home", color = Color.Black)
                }
            }
        }

        Button(
            onClick = {
                isFavoritesButtonClicked.value = true
                isHomeButtonClicked.value = false
            },
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .clip(RoundedCornerShape(topEnd = 16.dp)),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = beige)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_favorite_24),
                    contentDescription = "favorites button"
                )
                if (isFavoritesButtonClicked.value) {
                    Text(text = "Favorites", color = Color.Black)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageTopBar(
    viewModel: HomePageViewModel = hiltViewModel(),
    userManager: UserManagerDataStorage
) {

    val currentUserEmail = remember {
        mutableStateOf("")
    }

    val isProgressBarVisible = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    if (isProgressBarVisible.value) {
        CircularProgressIndicator(
            color = beige,
            modifier = Modifier.scale(1.5f),
            strokeWidth = ProgressIndicatorDefaults.CircularStrokeWidth
        )
    }

    LaunchedEffect(Unit) {
        viewModel.currentUser.collect { result ->
            when (result) {
                is Resource.Error -> {
                    isProgressBarVisible.value = false
                    currentUserEmail.value = "Unknown"
                }

                is Resource.Loading -> {
                    isProgressBarVisible.value = true
                }

                is Resource.Success -> {
                    isProgressBarVisible.value = false
                    currentUserEmail.value = result.data?.email.toString()
                    result.data?.email.let {
                        userManager.storeUserEmail(it ?: "Unknown")
                    }
                }
            }
        }
    }


    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(beige),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currentUserEmail.value,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                .background(beige),
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_24),
                contentDescription = "search a coin"
            )
        }

        IconButton(
            onClick = {
                viewModel.signOut()
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.ic_exit_to_app_24),
                contentDescription = "exit to app"
            )
        }
    }
}