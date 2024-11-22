package com.pogreb.pivmetr.repository.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pogreb.pivmetr.model.PivModel

@Entity(tableName = "Pivs")
class PivEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,
    @ColumnInfo(name = "namePiv")
    val namePiv: String = "",
    @ColumnInfo(name = "characteristic")
    val characteristic: String = "",
    @ColumnInfo(name = "rating")
    val rating: Float = 0F,
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "favorite")
    val favorite: Boolean = false,
) {
    fun toPiv(): PivModel = PivModel(
        id = id,
        name = namePiv,
        characteristic = characteristic,
        rating = rating,
        description = description,
        favorite = favorite,
    )

    companion object {
        fun fromPiv(piv: PivModel): PivEntity = with(piv) {
            PivEntity(
                id = piv.id,
                namePiv = piv.name,
                characteristic = piv.characteristic,
                rating = piv.rating,
                description = piv.description,
                favorite = piv.favorite,
            )
        }
    }
}