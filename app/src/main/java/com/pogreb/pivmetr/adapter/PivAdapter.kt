package com.pogreb.pivmetr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.pogreb.pivmetr.R
import com.pogreb.pivmetr.databinding.CardPivBinding
import com.pogreb.pivmetr.model.PivModel

class PivAdapter(
    private val listenerAdapter: ListenerAdapter
) : ListAdapter<PivModel, PivViewHolder>(PivDiffCallback()) {
    interface ListenerAdapter {
        fun onFavoriteClicked(piv: PivModel)
        fun onDeleteClicked(piv: PivModel)
        fun onEditClicked(piv: PivModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PivViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardPivBinding.inflate(layoutInflater, parent, false)

        val viewHolder = PivViewHolder(binding)

        binding.favorite.setOnClickListener {
            listenerAdapter.onFavoriteClicked(getItem(viewHolder.adapterPosition))
        }

        binding.menu.setOnClickListener {
            PopupMenu(it.context, it).apply {
                inflate(R.menu.edit_piv_menu)
                setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.delete -> {
                            listenerAdapter.onDeleteClicked(getItem(viewHolder.adapterPosition))
                            true
                        }

                        R.id.edit -> {
                            listenerAdapter.onEditClicked(getItem(viewHolder.adapterPosition))
                            true
                        }

                        else -> false
                    }
                }
                show()
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(
        holder: PivViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty()) {
            payloads.forEach {
                if (it is PivPayLoad) {
                    holder.bind(it)
                }
            }
        } else {
            onBindViewHolder(holder, position)
        }
    }

    override fun onBindViewHolder(holder: PivViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}