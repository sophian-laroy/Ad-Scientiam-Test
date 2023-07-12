package com.laroy.adscientiamtest.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.laroy.adscientiamtest.data.database.dao.PositionDao
import com.laroy.adscientiamtest.data.database.entity.PositionDatabase

@Database(
    entities = [PositionDatabase::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun positionDao(): PositionDao

    companion object {

        private const val NAME = "AdScientiamTestDatabase"

        fun createDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                NAME
            ).build()
        }
    }
}
