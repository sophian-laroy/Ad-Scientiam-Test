package com.laroy.adscientiamtest.data.source

import com.laroy.adscientiamtest.data.database.entity.PositionDatabase

interface PositionLocalDataSource {

    suspend fun save(positions: List<PositionDatabase>)

    suspend fun getAllPositions(isAsc: Boolean): List<PositionDatabase>

    suspend fun clearAll()

}
