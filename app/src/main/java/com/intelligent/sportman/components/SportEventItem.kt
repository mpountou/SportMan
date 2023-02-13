package com.intelligent.sportman.components

import android.os.CountDownTimer
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.intelligent.sportman.model.SportEvent
import java.text.SimpleDateFormat
import java.util.*

/**
 * Shows a SportEvent item of a given Sport category
 */
@Composable
fun SportEventItem(
    modifier: Modifier = Modifier,
    sportEvent: SportEvent,
    onAddFavorite: () -> Unit,
    onRemoveFavorite: () -> Unit,
    isFavorite: Boolean
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .widthIn(max = 100.dp)
            .heightIn(max = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SportEventTimer(startTime = sportEvent.startTime)

        EventFavoriteButton(
            isFavorite = isFavorite,
            onClick = {
                if (!isFavorite) {
                    onAddFavorite()
                } else {
                    onRemoveFavorite()
                }
            }
        )

        EventOpponentTeams(title = sportEvent.name)

    }
}


/**
 * Shows the remaining time of an upcoming sport event
 */
@Composable
fun SportEventTimer(
    modifier: Modifier = Modifier,
    startTime: Long
) {

    var timeRemaining by remember {
        mutableStateOf("HH:MM:SS")
    }

    DisposableEffect(startTime) {

        var timeFormat = SimpleDateFormat("DD:HH:mm:ss", Locale.getDefault())

        val currentTime = System.currentTimeMillis() / 1000

        var countDownTime = (startTime - currentTime) * 1000

        var timer = object : CountDownTimer(countDownTime, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = timeFormat.format(millisUntilFinished)
            }

            override fun onFinish() {
                timeRemaining = "00:00:00:00"
            }

        }.apply {
            start()
        }
        onDispose {
            timer.cancel()
        }
    }

    Text(
        text = timeRemaining,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(20)
            )
            .padding(4.dp),
        color = Color.White,
        fontSize = 12.sp,
        maxLines = 1,
    )

}

/**
 * A toggle button for favorite sport events
 */
@Composable
fun EventFavoriteButton(
    isFavorite: Boolean = false,
    onClick: () -> Unit = {}
) {

    val icon = if (isFavorite) Icons.Rounded.Star else Icons.Rounded.StarBorder
    val color = if (isFavorite) Color.Yellow else Color.Gray

    Icon(
        modifier = Modifier.clickable { onClick() },
        imageVector = icon,
        contentDescription = null,
        tint = color,
    )

}


/**
 * Shows the opponent teams of a sport event
 */
@Composable
fun EventOpponentTeams(
    title: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        title.split(" - ").forEach {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = it,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Preview
@Composable
fun SportEventItemPrev() {
    val sportEvent = SportEvent(
        id = "29135390",
        name = "Juventus FC - Paris Saint-Germain",
        sportId = "FOOT",
        startTime = 1667447160,
    )
    SportEventItem(
        sportEvent = sportEvent,
        onAddFavorite = {},
        onRemoveFavorite = {},
        isFavorite = true
    )
}