package com.laroy.adscientiamtest.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Upsert
import com.laroy.adscientiamtest.data.database.entity.PositionDatabase

@Dao
interface PositionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg position: PositionDatabase)

}
