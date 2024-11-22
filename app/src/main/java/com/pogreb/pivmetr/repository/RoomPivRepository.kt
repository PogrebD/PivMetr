package com.pogreb.pivmetr.repository

import com.pogreb.pivmetr.model.PivModel
import com.pogreb.pivmetr.repository.dataBase.PivDao
import com.pogreb.pivmetr.repository.dataBase.PivEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomPivRepository(private val dao: PivDao) : PivRepository {
    override fun getPiv(): Flow<List<PivModel>> = dao.getAll()
        .map {
            it.map(PivEntity::toPiv)
        }

    override fun favoriteById(id: Long) {
        dao.favoriteById(id)
    }

    override fun savePiv(id: Long, description: String) {
        dao.save(PivEntity.fromPiv(PivModel(id = id, description = description)))
    }

    override fun editById(id: Long, description: String) {
        dao.changeDescriptionById(id = id, description = description)
    }

}