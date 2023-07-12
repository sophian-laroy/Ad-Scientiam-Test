package com.laroy.adscientiamtest.data.database.dao

import androidx.room.*
import com.laroy.adscientiamtest.data.database.entity.PositionDatabase

@Dao
interface PositionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg position: PositionDatabase)

    @Query("SELECT * FROM Position ORDER BY " +
            "CASE WHEN :isAsc = 1 THEN date END ASC, " +
            "CASE WHEN :isAsc = 0 THEN date END DESC")
    suspend fun getAll(isAsc: Boolean): List<PositionDatabase>

    @Query("DELETE FROM Position")
    suspend fun deleteAll()

}
