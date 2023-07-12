package com.laroy.adscientiamtest.domain.repository

import com.laroy.adscientiamtest.data.database.entity.PositionDatabase
import kotlinx.coroutines.flow.Flow

interface PositionRepository {

    suspend fun savePositionsInDatabase(positions: List<PositionDatabase>)

    suspend fun getAllPositionsInDatabase(): List<PositionDatabase>

}
