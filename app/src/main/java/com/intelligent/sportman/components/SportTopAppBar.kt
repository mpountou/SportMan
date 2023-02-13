package com.intelligent.sportman.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FilterAlt
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.intelligent.sportman.R

/**
 * Sport Top App bar with default app name used in scaffold
 */
@Composable
fun SportTopAppBar(
    title: String = stringResource(id = R.string.app_name),
    onAllEventsClicked: () -> Unit,
    upcomingEventsClicked: () -> Unit,
    likedEventsClicked: () -> Unit

) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            TopAppBarActions(
                onAllEventsClicked = onAllEventsClicked,
                upcomingEventsClicked = upcomingEventsClicked,
                likedEventsClicked = likedEventsClicked
            )
        }
    )
}

/**
 * Contains all Top app bar actions.
 * In our case only FilterAction button
 */
@Composable
fun TopAppBarActions(
    onAllEventsClicked: () -> Unit,
    upcomingEventsClicked: () -> Unit,
    likedEventsClicked: () -> Unit
) {
    FilterAction(
        onAllEventsClicked = onAllEventsClicked,
        upcomingEventsClicked = upcomingEventsClicked,
        likedEventsClicked = likedEventsClicked
    )
}

/**
 * A drop down menu that contains
 * all filter mode options for event sports
 */
@Composable
fun FilterAction(
    onAllEventsClicked: () -> Unit,
    upcomingEventsClicked: () -> Unit,
    likedEventsClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            imageVector = Icons.Rounded.FilterAlt,
            contentDescription = null,
            tint = Color.White
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onAllEventsClicked()
                }
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    text = stringResource(id = R.string.all_events),
                    style = MaterialTheme.typography.subtitle2
                )
            }
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    upcomingEventsClicked()
                }
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    text = stringResource(id = R.string.upcoming_events),
                    style = MaterialTheme.typography.subtitle2
                )
            }

            DropdownMenuItem(
                onClick = {
                    expanded = false
                    likedEventsClicked()
                }
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp),
                    text = stringResource(id = R.string.liked_events),
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}

@Preview
@Composable
fun SportTopAppBarPrev() {
    SportTopAppBar(
        onAllEventsClicked = {},
        upcomingEventsClicked = {},
        likedEventsClicked = {}
    )
}