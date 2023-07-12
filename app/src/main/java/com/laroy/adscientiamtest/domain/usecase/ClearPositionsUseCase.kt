package com.laroy.adscientiamtest.domain.usecase

import com.laroy.adscientiamtest.domain.model.Position
import com.laroy.adscientiamtest.domain.model.PositionDir
import com.laroy.adscientiamtest.domain.repository.PositionRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ClearPositionsUseCase @Inject constructor(
    private val positionRepository: PositionRepository
) {

    suspend operator fun invoke() {
        positionRepository.clearPositionsInDatabase()
    }
}