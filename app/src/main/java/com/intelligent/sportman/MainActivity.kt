package com.intelligent.sportman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.intelligent.sportman.ui.screens.SportManApp
import com.intelligent.sportman.ui.theme.SportManTheme
import com.intelligent.sportman.ui.viewmodel.SportManViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<SportManViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportManTheme {
                SportManApp(viewModel = viewModel)
            }
        }
    }
}

