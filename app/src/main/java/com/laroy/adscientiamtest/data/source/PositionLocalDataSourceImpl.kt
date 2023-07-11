package com.laroy.adscientiamtest.data.source

import com.laroy.adscientiamtest.data.database.dao.PositionDao
import com.laroy.adscientiamtest.data.database.entity.PositionDatabase

class PositionLocalDataSourceImpl(private val positionDao: PositionDao) : PositionLocalDataSource {

    override suspend fun save(positions: List<PositionDatabase>) {
        positionDao.save(*positions.toTypedArray())
    }

}
