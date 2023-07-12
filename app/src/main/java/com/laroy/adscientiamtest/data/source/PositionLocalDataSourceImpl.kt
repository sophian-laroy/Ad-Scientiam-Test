package com.laroy.adscientiamtest.data.source

import com.laroy.adscientiamtest.data.database.dao.PositionDao
import com.laroy.adscientiamtest.data.database.entity.PositionDatabase
import kotlinx.coroutines.flow.Flow

class PositionLocalDataSourceImpl(private val positionDao: PositionDao) : PositionLocalDataSource {

    override suspend fun save(positions: List<PositionDatabase>) {
        positionDao.save(*positions.toTypedArray())
    }

    override suspend fun getAllPositions(isAsc: Boolean): List<PositionDatabase> {
        return positionDao.getAll(isAsc)
    }

}
