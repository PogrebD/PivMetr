package com.pogreb.pivmetr.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.pogreb.pivmetr.R
import com.pogreb.pivmetr.databinding.FragmentBottomMenuBinding

class BottomMenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBottomMenuBinding.inflate(inflater, container, false)

        val newPivListener = View.OnClickListener {
            findNavController().graph.nodes[R.id.newPivFragment]?.label = "New Piv"
            findNavController().navigate(R.id.action_bottomMenuFragment_to_newPivFragment)
            findNavController().graph.nodes[R.id.newPivFragment]?.label = "Change description"
        }

        val navController =
            requireNotNull(childFragmentManager.findFragmentById(R.id.container)).findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.pivsFragment -> {

                    binding.add.setOnClickListener(newPivListener)
                    binding.add.animate().scaleX(1F).scaleY(1F)
                }

                R.id.calendarFragment -> {
                    binding.add.setOnClickListener(null)
                    binding.add.animate().scaleX(0F).scaleY(0F)
                }

                R.id.profileFragment -> {
                    binding.add.setOnClickListener(null)
                    binding.add.animate().scaleX(0F).scaleY(0F)
                }
            }
        }
        binding.bottomNavMenu.setupWithNavController(navController)

        return binding.root
    }
}