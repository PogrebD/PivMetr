package com.pogreb.pivmetr.adapter

data class PivPayLoad(
    val favorite: Boolean? = null,
) {
    fun isNotEmpty(): Boolean = favorite != null
}
