package com.intelligent.sportman.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.intelligent.sportman.model.Sport
import com.intelligent.sportman.model.SportEvent
import com.intelligent.sportman.ui.theme.BlueGray
import com.intelligent.sportman.util.Mode
import com.intelligent.sportman.util.filterMode

/**
 * Shows a Sport Category item with
 * the current upcoming sport events
 */
@Composable
fun SportCardItem(
    sport: Sport,
    onAddFavorite: (SportEvent) -> Unit,
    onRemoveFavorite: (SportEvent) -> Unit,
    modifier: Modifier = Modifier,
    mode: Mode
) {
    var isExpanded by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier.animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {

        CardHeader(
            sportName = sport.name,
            isExpanded = isExpanded,
            onExpandClick = { isExpanded = !isExpanded }
        )

        if (isExpanded) {
            CardContent(
                sportEvents = sport.sportEvents,
                favoriteSportEvents = sport.favoriteSportEvents,
                onAddFavorite = onAddFavorite,
                onRemoveFavorite = onRemoveFavorite,
                mode = mode
            )
        }

    }

}

/**
 * Header of sport card item.
 * Contains title and expandable button
 */
@Composable
fun CardHeader(
    sportName: String,
    isExpanded: Boolean,
    onExpandClick: () -> Unit
) {

    val expandIcon = if (!isExpanded) Icons.Rounded.ExpandMore else Icons.Rounded.ExpandLess

    Row(
        modifier = Modifier
            .heightIn(30.dp)
            .fillMaxWidth()
            .background(BlueGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = sportName,
            color = Color.White
        )
        Icon(
            modifier = Modifier
                .padding(5.dp)
                .clickable { onExpandClick() },
            imageVector = expandIcon,
            contentDescription = null,
            tint = Color.White
        )
    }
}

/**
 * Content of sport card.
 * Contains the upcoming sport events
 */
@Composable
fun CardContent(
    sportEvents: List<SportEvent>,
    favoriteSportEvents: List<SportEvent>,
    onAddFavorite: (SportEvent) -> Unit,
    onRemoveFavorite: (SportEvent) -> Unit,
    mode: Mode
) {
    LazyRow {
        val likedEvents = filterMode(mode, favoriteSportEvents, isLikedList = true)
        items(likedEvents) { eventItem ->
            SportEventItem(
                sportEvent = eventItem,
                isFavorite = true,
                onAddFavorite = { onAddFavorite(eventItem) },
                onRemoveFavorite = { onRemoveFavorite(eventItem) }
            )
        }
        val events = filterMode(mode, sportEvents)
        items(events) { eventItem ->
            SportEventItem(
                sportEvent = eventItem,
                isFavorite = false,
                onAddFavorite = { onAddFavorite(eventItem) },
                onRemoveFavorite = { onRemoveFavorite(eventItem) }
            )
        }

    }
}


@Preview
@Composable
fun SportCardItemPreview() {
    val sportEvent1 = SportEvent(
        id = "29135368",
        name = "AC Milan - FC Red Bull Salzburg",
        sportId = "FOOT",
        startTime = 1672026900,
    )
    val sportEvent2 = SportEvent(
        id = "29135390",
        name = "Juventus FC - Paris Saint-Germain",
        sportId = "FOOT",
        startTime = 1667447160,
    )
    val sportEvent = Sport(
        id = "",
        name = "SOCCER",
        sportEvents = listOf(sportEvent1, sportEvent2)
    )
    SportCardItem(
        sport = sportEvent,
        onAddFavorite = {},
        onRemoveFavorite = {},
        mode = Mode.ALL
    )
}


