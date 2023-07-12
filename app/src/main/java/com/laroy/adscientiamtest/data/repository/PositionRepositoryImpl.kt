package com.laroy.adscientiamtest.data.repository

import com.laroy.adscientiamtest.data.database.entity.PositionDatabase
import com.laroy.adscientiamtest.data.source.PositionLocalDataSource
import com.laroy.adscientiamtest.domain.repository.PositionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PositionRepositoryImpl @Inject constructor(
    private val positionLocalDataSource: PositionLocalDataSource
) : PositionRepository {

    override suspend fun savePositionsInDatabase(positions: List<PositionDatabase>) {
        positionLocalDataSource.save(positions)
    }

    override suspend fun getAllPositionsInDatabase(): List<PositionDatabase> {
        return positionLocalDataSource.getAllPositions()
    }

}
