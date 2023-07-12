package com.laroy.adscientiamtest.domain.usecase

import com.laroy.adscientiamtest.domain.model.Position
import com.laroy.adscientiamtest.domain.repository.PositionRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPositionsUseCase @Inject constructor(
    private val positionRepository: PositionRepository
) {

    operator fun invoke() = flow {
        emit(
            positionRepository.getAllPositionsInDatabase().map { positionDatabase ->
                Position(
                    date = positionDatabase.date,
                    x = positionDatabase.x,
                    y = positionDatabase.y
                )
            }
        )
    }
}