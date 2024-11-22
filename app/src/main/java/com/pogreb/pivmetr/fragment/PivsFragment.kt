package com.pogreb.pivmetr.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.fragment.findNavController
import com.pogreb.pivmetr.R
import com.pogreb.pivmetr.adapter.PivAdapter
import com.pogreb.pivmetr.databinding.FragmentPivsBinding
import com.pogreb.pivmetr.itemDecoration.OffsetDecoration
import com.pogreb.pivmetr.model.PivModel
import com.pogreb.pivmetr.repository.RoomPivRepository
import com.pogreb.pivmetr.repository.dataBase.AppDb
import com.pogreb.pivmetr.viewModel.PivViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PivsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPivsBinding.inflate(inflater, container, false)

        val viewModel by viewModels<PivViewModel> {
            viewModelFactory {
                initializer {
                    PivViewModel(
                        RoomPivRepository(
                            AppDb.getInstance(
                                requireContext().applicationContext
                            ).pivsDao
                        )
                    )
                }
            }
        }

        val adapter = PivAdapter(object : PivAdapter.ListenerAdapter {
            override fun onFavoriteClicked(piv: PivModel) {
                viewModel.favoriteById(piv.id)
            }

            override fun onDeleteClicked(piv: PivModel) {
                viewModel.deleteById(piv.id)
            }

            override fun onEditClicked(piv: PivModel) {
                requireParentFragment().requireParentFragment()
                    .findNavController()
                    .navigate(
                        R.id.action_pivsFragment_to_newPivFragment,
                        bundleOf(
                            NewPivFragment.PIV_ID to piv.id,
                            NewPivFragment.PIV_DESCRIPTION to piv.description,
                        )
                    )
            }
        })

        binding.listPiv.adapter = adapter

        binding.listPiv.addItemDecoration(
            OffsetDecoration(resources.getDimensionPixelOffset(R.dimen.small_spacing))
        )

        viewModel.uiState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                adapter.submitList(it.pivs)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }

}