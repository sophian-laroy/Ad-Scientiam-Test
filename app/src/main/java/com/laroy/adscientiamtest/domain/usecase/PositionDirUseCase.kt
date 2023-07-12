package com.laroy.adscientiamtest.domain.usecase

import com.laroy.adscientiamtest.domain.model.PositionDir
import com.laroy.adscientiamtest.domain.repository.PositionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PositionDirUseCase @Inject constructor(
    private val positionRepository: PositionRepository
) {

    suspend fun update(positionDir: PositionDir) {
        positionRepository.updatePositionDir(positionDir)
    }

    fun get(): Flow<PositionDir> {
        return positionRepository.getPositionDir()
    }

}