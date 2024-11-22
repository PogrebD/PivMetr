package com.pogreb.pivmetr.repository.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.pogreb.pivmetr.repository.PivRepository
import kotlinx.coroutines.flow.Flow

@Dao
interface PivDao {
    @Query("SELECT * FROM Pivs ORDER BY id DESC")
    fun getAll(): Flow<List<PivEntity>>

    @Query(
        """
        UPDATE Pivs SET 
                favorite = CASE WHEN favorite THEN 0 ELSE 1 END 
            WHERE id = :id;
    """
    )
    fun favoriteById(id: Long)

    @Upsert()
    fun save(piv: PivEntity)

    @Query(
        """
        UPDATE Pivs SET 
                description = :description 
            WHERE id = :id;
    """
    )
    fun changeDescriptionById(id: Long, description: String )
}