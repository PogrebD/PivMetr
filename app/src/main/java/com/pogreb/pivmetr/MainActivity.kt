package com.pogreb.pivmetr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pogreb.pivmetr.adapter.PivAdapter
import com.pogreb.pivmetr.databinding.ActivityMainBinding
import com.pogreb.pivmetr.itemDecoration.OffsetDecoration
import com.pogreb.pivmetr.repository.InMemoryPivRepository
import com.pogreb.pivmetr.viewModel.PivViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PivViewModel> {
            viewModelFactory {
                initializer { PivViewModel(InMemoryPivRepository()) }
            }
        }

        val adapter = PivAdapter {
            viewModel.favoriteById(it.id)
        }

        binding.root.adapter = adapter

        binding.root.addItemDecoration(
            OffsetDecoration(resources.getDimensionPixelOffset(R.dimen.small_spacing))
        )

        viewModel.uiState.flowWithLifecycle(lifecycle)
            .onEach {
                adapter.submitList(it.pivs)
            }
            .launchIn(lifecycleScope)
    }
}