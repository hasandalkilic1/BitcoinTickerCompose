package com.example.bitcointickercompose.presentation.DetailPage

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.bitcointickercompose.R
import com.example.bitcointickercompose.components.bounceClick
import com.example.bitcointickercompose.presentation.theme.beige
import com.example.bitcointickercompose.presentation.theme.light_Green
import com.example.bitcointickercompose.utils.UIScreen
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailPage(navController: NavController, viewModel: DetailPageViewModel = hiltViewModel()) {

    val detailPageState = viewModel.coinDetail.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(light_Green)
    ) {
        DetailPageTopBar(navController = navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(light_Green)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 72.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {

                // Coin Image
                Image(
                    painter = rememberImagePainter(data = detailPageState.coinDetail?.image),
                    contentDescription = "Coin Image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 8.dp)
                )

                // Favorites Button
                Button(
                    onClick = {
                        detailPageState.coinDetail?.let { viewModel.addToFavorites(it) }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .padding(start = 32.dp)
                        .bounceClick()
                ) {
                    Text(
                        text = "Add To Favorites",
                        color = Color.White,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite_24),
                        contentDescription = "add to favorites image"
                    )
                }
            }

            // Coin Name
            Text(
                text = detailPageState.coinDetail?.name ?: "Unknown",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            // Hashing Algorithm
            Text(
                text = ("Hashing Algorithm: " + detailPageState.coinDetail?.hashingAlgorithm),
                fontSize = 8.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
            )

            // Current Price
            Text(
                text = "Current Price: " + viewModel.coinDetail.value.coinDetail?.currentPrice,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
            )

            // Price Change Percentage in 24h
            Text(
                text = "Price Change Percentage in 24h: " + detailPageState.coinDetail?.priceChangePercentage24h.toString(),
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth()
            )

            // Description
            Text(
                text = "Description:",
                style = TextStyle(textDecoration = TextDecoration.Underline),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            )

            // Coin Description
            Text(
                text = detailPageState.coinDetail?.description ?: "Unknown",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun DetailPageTopBar(
    viewModel: DetailPageViewModel = hiltViewModel(),
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(1f)
            .height(64.dp)
            .clip(
                RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )
            .background(beige),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

            val (icon, coinName) = createRefs()
            Icon(
                painter = painterResource(id = R.drawable.ic_back_black_24),
                contentDescription = "Back Image",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable { navController.popBackStack() }
                    .constrainAs(icon) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = viewModel.coinDetail.value.coinDetail?.name ?: "Unknown",
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.constrainAs(coinName) {
                    top.linkTo(parent.top)
                    start.linkTo(icon.end)
                    centerHorizontallyTo(parent)
                }
            )
        }
    }
}