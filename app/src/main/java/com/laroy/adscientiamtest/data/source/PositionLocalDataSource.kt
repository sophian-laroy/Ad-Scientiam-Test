package com.laroy.adscientiamtest.data.source

import com.laroy.adscientiamtest.data.database.entity.PositionDatabase
import kotlinx.coroutines.flow.Flow

interface PositionLocalDataSource {

    suspend fun save(positions: List<PositionDatabase>)

}
