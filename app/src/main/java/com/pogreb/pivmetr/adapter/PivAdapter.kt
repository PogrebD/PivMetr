package com.pogreb.pivmetr.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.pogreb.pivmetr.databinding.CardPivBinding
import com.pogreb.pivmetr.model.PivModel

class PivAdapter(
    private val favoriteClickListener: (PivModel) -> Unit
) : ListAdapter<PivModel, PivViewHolder>(PivDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PivViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardPivBinding.inflate(layoutInflater, parent, false)

        val viewHolder = PivViewHolder(binding)

        binding.favorite.setOnClickListener {
            favoriteClickListener(getItem(viewHolder.adapterPosition))
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