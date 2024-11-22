package com.pogreb.pivmetr.repository.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PivEntity::class],
    version = 1
)
abstract class AppDb:RoomDatabase() {
    abstract val pivsDao:PivDao
    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getInstance(context: Context): AppDb {
            INSTANCE?.let { return it }

            val application = context.applicationContext

            synchronized(this) {
                INSTANCE?.let { return it }
                val appDb = Room.databaseBuilder(application, AppDb::class.java, "db_app")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = appDb
                return appDb
            }
        }
    }
}