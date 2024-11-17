package com.pogreb.pivmetr.model

data class PivModel(
    val id: Long = 0L,
    val name: String = "",
    val characteristic: String = "",
    val rating: Float = 0F,
    val description: String = "",
    val favorite: Boolean = false,
)