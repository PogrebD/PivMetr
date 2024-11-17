package com.pogreb.pivmetr.adapter

import androidx.recyclerview.widget.RecyclerView
import com.pogreb.pivmetr.R
import com.pogreb.pivmetr.databinding.CardPivBinding
import com.pogreb.pivmetr.model.PivModel

class PivViewHolder(
    private val binding: CardPivBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pivModel: PivModel) {
        updateFavorite(pivModel.favorite)
        binding.description.text = pivModel.description
        binding.additionalInformation.text = pivModel.characteristic
        binding.rating.rating = pivModel.rating
        binding.name.text = pivModel.name
    }

    fun bind(payLoad: PivPayLoad) {
        if (payLoad.favorite != null) {
            updateFavorite(payLoad.favorite)
        }
    }

    private fun updateFavorite(favoriteByMe: Boolean) {
        binding.favorite.setImageResource(
            if(favoriteByMe){
                R.drawable.baseline_star_24
            } else {
                R.drawable.baseline_star_outline_24
            }
        )
    }
}