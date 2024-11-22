package com.pogreb.pivmetr.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.pogreb.pivmetr.R
import com.pogreb.pivmetr.databinding.FragmentNewPivBinding.inflate
import com.pogreb.pivmetr.databinding.FragmentNewPivBinding
import com.pogreb.pivmetr.repository.RoomPivRepository
import com.pogreb.pivmetr.repository.dataBase.AppDb
import com.pogreb.pivmetr.utils.toast
import com.pogreb.pivmetr.viewModel.NewPivViewModel
import com.pogreb.pivmetr.viewModel.ToolbarViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NewPivFragment : Fragment() {
    companion object {
        const val PIV_ID = "PIV_ID"
        const val PIV_DESCRIPTION = "PIV_DESCRIPTION"
    }
    private val toolbarViewModel by activityViewModels<ToolbarViewModel>()
    override fun onStart() {
        super.onStart()
        toolbarViewModel.setSaveVisibility(true)
    }

    override fun onStop() {
        super.onStop()
        toolbarViewModel.setSaveVisibility(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        val binding = FragmentNewPivBinding.inflate(layoutInflater)

        val id = arguments?.getLong(PIV_ID) ?: 0L
        val editableContent = arguments?.getString(PIV_DESCRIPTION)

        if (id != 0L) {
            if (editableContent != null) {
                binding.content.setText(editableContent)
            }
        }

        val viewModel by viewModels<NewPivViewModel> {
            viewModelFactory {
                initializer {
                    NewPivViewModel(
                        repository = RoomPivRepository(
                            AppDb.getInstance(
                                requireContext().applicationContext
                            ).pivsDao
                        ),
                        id = id
                    )
                }
            }
        }

        toolbarViewModel.saveClicked
            .filter {
                it
            }
            .onEach {
                val content = binding.content.text?.toString().orEmpty()

                if (content.isBlank()) {
                    requireContext().toast(R.string.empty_description, true)
                } else {
                    if (id != 0L) {
                        viewModel.edit(content)
                    } else {
                        viewModel.save(content)
                    }
                    findNavController().navigateUp()
                }
                toolbarViewModel.saveClicked(false)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }
}