package com.intelligent.sportman.ui.content

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.intelligent.sportman.R

@Composable
fun SportManLoading() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_respone))
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LottieAnimation(
            composition,
            modifier = Modifier.height(200.dp).widthIn(),
            iterations = LottieConstants.IterateForever
        )

    }
}