package com.laroy.adscientiamtest.domain.usecase

import com.laroy.adscientiamtest.domain.repository.PositionRepository
import javax.inject.Inject

class ClearPositionsUseCase @Inject constructor(
    private val positionRepository: PositionRepository
) {

    suspend operator fun invoke() {
        positionRepository.clearPositionsInDatabase()
    }
}