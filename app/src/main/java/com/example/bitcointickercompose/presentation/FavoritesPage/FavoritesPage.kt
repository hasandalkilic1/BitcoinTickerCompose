package com.example.bitcointickercompose.presentation.FavoritesPage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bitcointickercompose.R
import com.example.bitcointickercompose.domain.model.Favorites
import com.example.bitcointickercompose.presentation.Screen
import com.example.bitcointickercompose.presentation.theme.beige
import com.example.bitcointickercompose.presentation.theme.light_Green

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritesPage(navController: NavController, viewModel: FavoritesViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(light_Green)
    ) {
        // Top Bar
        Scaffold(
            topBar = { FavoritesPageTopBar() }, modifier = Modifier
                .height(64.dp)
                .clip(
                    RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .background(beige)
                .fillMaxWidth()
        ) {}

        FavoritesPageCoinsColumn()

        // Bottom Buttons
        Scaffold(
            bottomBar = { FavoritesPageBottomButtons(navController = navController) },
            modifier = Modifier
                .height(64.dp)
                .clip(
                    RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .align(Alignment.BottomCenter)
                .background(beige)
        ) {}

    }
}

@Composable
fun FavoritesPageCoinsColumn(viewModel: FavoritesViewModel = hiltViewModel()) {
    val favoritesState = viewModel.favoritesState.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp, bottom = 64.dp)
    ) {
        items(favoritesState.favorites) { coin ->
            FavoritesCoinRowItem(coin = coin)
        }
    }
}

@Composable
fun FavoritesCoinRowItem(coin: Favorites, viewModel: FavoritesViewModel = hiltViewModel()) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp)
        ) {
            val (coinName, coinImage, currentPrice, priceChangePercentage24h, deleteIcon) = createRefs()

            Text(text = coin.name ?: "Unknown",
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(4.dp)
                    .constrainAs(coinName) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                    })

            Image(painter = rememberImagePainter(data = coin.image),
                contentDescription = "Coin Image",
                modifier = Modifier
                    .size(30.dp)
                    .padding(4.dp)
                    .constrainAs(coinImage) {
                        top.linkTo(coinName.bottom)
                        start.linkTo(parent.start)
                        bottom.linkTo(parent.bottom)
                    })

            Text(text = coin.currentPrice.toString(),
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(4.dp)
                    .constrainAs(currentPrice) {
                        top.linkTo(parent.top)
                        bottom.linkTo(priceChangePercentage24h.top)
                        end.linkTo(deleteIcon.start)
                    }
            )

            Text(text = String.format("%.2f", coin.priceChangePercentage24h) + "%",
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(4.dp)
                    .constrainAs(priceChangePercentage24h) {
                        top.linkTo(currentPrice.bottom)
                        end.linkTo(deleteIcon.start)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_delete_red_24),
                contentDescription = "Delete Coin Icon",
                modifier = Modifier
                    .clickable {
                        viewModel.deleteFromFavorites(coin)
                        viewModel.getFavorites()
                    }
                    .padding(4.dp)
                    .constrainAs(deleteIcon) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        centerVerticallyTo(parent)
                    }
            )
        }
    }
}

@Composable
fun FavoritesPageTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(1f)
            .height(64.dp)
            .clip(
                RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )
            .background(beige),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Favorites",
            color = Color.Black,
            fontSize = 20.sp,
        )
    }
}

@Composable
fun FavoritesPageBottomButtons(navController: NavController) {
    val isHomeButtonClicked = remember {
        mutableStateOf(false)
    }

    val isFavoritesButtonClicked = remember {
        mutableStateOf(true)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        // Home Button
        Button(
            onClick = {
                isHomeButtonClicked.value = true
                isFavoritesButtonClicked.value = false
                navController.navigate(Screen.HomePage.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
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

        // Favorites Button
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