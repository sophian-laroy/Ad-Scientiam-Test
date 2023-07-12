package com.laroy.adscientiamtest.domain.repository

import com.laroy.adscientiamtest.data.database.entity.PositionDatabase
import com.laroy.adscientiamtest.domain.model.PositionDir
import kotlinx.coroutines.flow.Flow

interface PositionRepository {

    suspend fun savePositionsInDatabase(positions: List<PositionDatabase>)

    suspend fun getAllPositionsInDatabase(isAsc: Boolean): List<PositionDatabase>

    suspend fun updatePositionDir(positionDir: PositionDir)

    fun getPositionDir(): Flow<PositionDir>

    suspend fun clearPositionsInDatabase()

}
