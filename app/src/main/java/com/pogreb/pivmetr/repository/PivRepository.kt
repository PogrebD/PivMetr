package com.pogreb.pivmetr.repository

import com.pogreb.pivmetr.model.PivModel
import kotlinx.coroutines.flow.Flow

interface PivRepository {
    fun getPiv(): Flow<List<PivModel>>
    fun favoriteById(id:Long)
}