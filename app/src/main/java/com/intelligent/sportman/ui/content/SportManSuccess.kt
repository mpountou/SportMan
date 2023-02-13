package com.intelligent.sportman.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.intelligent.sportman.components.SportCardItem
import com.intelligent.sportman.model.Sport
import com.intelligent.sportman.ui.viewmodel.SportManViewModel
import com.intelligent.sportman.util.Mode

@Composable
fun SportManSuccess(
    sports: List<Sport>,
    viewModel: SportManViewModel,
    mode: Mode
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        itemsIndexed(sports) { sportIndex, item ->
            SportCardItem(
                sport = item,
                onAddFavorite = { sportEvent ->
                    viewModel.addFavorite(sportIndex, sportEvent)
                },
                onRemoveFavorite = { sportEvent ->
                    viewModel.removeFavorite(sportIndex, sportEvent)
                },
                mode = mode
            )
        }
    }
}