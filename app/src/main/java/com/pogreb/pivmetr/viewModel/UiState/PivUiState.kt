package com.pogreb.pivmetr.viewModel.UiState

import com.pogreb.pivmetr.model.PivModel

data class PivUiState(
    val pivs: List<PivModel> = emptyList()
)
