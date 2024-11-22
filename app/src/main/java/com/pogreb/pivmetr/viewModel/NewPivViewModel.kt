package com.pogreb.pivmetr.viewModel

import androidx.lifecycle.ViewModel
import com.pogreb.pivmetr.repository.PivRepository

class NewPivViewModel(
    private val repository: PivRepository,
    private val id: Long,
) : ViewModel() {

    fun save(description: String) {
        repository.savePiv(id, description)
    }

    fun edit(description: String) {
        repository.editById(id, description)
    }
}