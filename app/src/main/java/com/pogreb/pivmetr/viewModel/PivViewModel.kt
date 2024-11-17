package com.pogreb.pivmetr.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pogreb.pivmetr.repository.PivRepository
import com.pogreb.pivmetr.viewModel.UiState.PivUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class PivViewModel(private val repository: PivRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(PivUiState())
    val uiState: StateFlow<PivUiState> = _uiState.asStateFlow()

    init {
        repository.getPiv()
            .onEach { pivs ->
                _uiState.update {
                    it.copy(pivs = pivs)
                }
            }
            .launchIn(viewModelScope)
    }

    fun favoriteById(id:Long) {
        repository.favoriteById(id)
    }
}