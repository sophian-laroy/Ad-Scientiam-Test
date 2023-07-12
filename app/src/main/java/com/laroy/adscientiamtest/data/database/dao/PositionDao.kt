package com.laroy.adscientiamtest.data.database.dao

import androidx.room.*
import com.laroy.adscientiamtest.data.database.entity.PositionDatabase

@Dao
interface PositionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg position: PositionDatabase)

    @Query("SELECT * FROM Position")
    suspend fun getAll(): List<PositionDatabase>

}
