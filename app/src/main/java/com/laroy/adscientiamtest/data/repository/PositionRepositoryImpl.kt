package com.laroy.adscientiamtest.data.repository

import com.laroy.adscientiamtest.data.database.entity.PositionDatabase
import com.laroy.adscientiamtest.data.source.PositionLocalDataSource
import com.laroy.adscientiamtest.domain.model.PositionDir
import com.laroy.adscientiamtest.domain.repository.PositionRepository
import com.laroy.adscientiamtest.utils.AppDatastore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PositionRepositoryImpl @Inject constructor(
    private val positionLocalDataSource: PositionLocalDataSource,
    private val datastore: AppDatastore
) : PositionRepository {

    override suspend fun savePositionsInDatabase(positions: List<PositionDatabase>) {
        positionLocalDataSource.save(positions)
    }

    override suspend fun getAllPositionsInDatabase(isAsc: Boolean): List<PositionDatabase> {
        return positionLocalDataSource.getAllPositions(isAsc)
    }

    override suspend fun updatePositionDir(positionDir: PositionDir) {
        datastore.saveOrderDir(positionDir)
    }

    override fun getPositionDir(): Flow<PositionDir> {
        return datastore.getPositionDir
    }

}
