package com.pogreb.pivmetr.repository

import com.pogreb.pivmetr.model.PivModel
import kotlinx.coroutines.flow.Flow

interface PivRepository {
    fun getPiv(): Flow<List<PivModel>>
    fun favoriteById(id:Long)
    fun savePiv(id: Long, description: String)
    fun editById(id: Long, description: String)
}