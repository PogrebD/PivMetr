package com.pogreb.pivmetr.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pogreb.pivmetr.model.PivModel

class PivDiffCallback : DiffUtil.ItemCallback<PivModel>() {
    override fun areItemsTheSame(oldItem: PivModel, newItem: PivModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PivModel, newItem: PivModel): Boolean =
        oldItem == newItem

    override fun getChangePayload(oldItem: PivModel, newItem: PivModel): Any? =
        PivPayLoad(
            favorite = newItem.favorite.takeIf {
                it != oldItem.favorite
            },
        )
            .takeIf {
                it.isNotEmpty()
            }
}