package com.intelligent.sportman.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.intelligent.sportman.components.SportTopAppBar
import com.intelligent.sportman.model.RequestState
import com.intelligent.sportman.ui.content.SportManError
import com.intelligent.sportman.ui.content.SportManLoading
import com.intelligent.sportman.ui.content.SportManSuccess
import com.intelligent.sportman.ui.theme.BlueGrayDark
import com.intelligent.sportman.ui.viewmodel.SportManViewModel
import com.intelligent.sportman.util.Mode

@Composable
fun SportManApp(viewModel: SportManViewModel) {
    Scaffold(
        topBar = {
            SportTopAppBar(
                onAllEventsClicked = { viewModel.updateMode(Mode.ALL) },
                upcomingEventsClicked = { viewModel.updateMode(Mode.UPCOMING) },
                likedEventsClicked = { viewModel.updateMode(Mode.LIKED) }
            )
        },
        content = { SportManContent(viewModel = viewModel) }
    )
}

@Composable
fun SportManContent(
    viewModel: SportManViewModel
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(BlueGrayDark)
    ) {

        val sports by viewModel.sports.observeAsState(listOf())
        val requestState by viewModel.requestState.observeAsState(RequestState.INITIAL)
        val mode by viewModel.mode.observeAsState(Mode.ALL)

        LaunchedEffect(Unit) {
            viewModel.getSports()
        }

        when (requestState) {
            RequestState.LOADING -> {
                SportManLoading()
            }
            RequestState.SUCCESS -> {
                SportManSuccess(
                    sports = sports,
                    viewModel = viewModel,
                    mode = mode
                )
            }
            RequestState.ERROR -> {
                SportManError(
                    onRetry = {
                        viewModel.getSports()
                    }
                )
            }
        }
    }
}