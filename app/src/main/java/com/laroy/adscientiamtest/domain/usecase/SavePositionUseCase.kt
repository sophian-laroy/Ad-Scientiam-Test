package com.laroy.adscientiamtest.domain.usecase

import com.laroy.adscientiamtest.data.database.entity.PositionDatabase
import com.laroy.adscientiamtest.domain.model.Position
import com.laroy.adscientiamtest.domain.repository.PositionRepository
import java.util.*
import javax.inject.Inject

class SavePositionUseCase @Inject constructor(
    private val positionRepository: PositionRepository
) {

    suspend operator fun invoke(positionsToSave: List<Position>) {
        positionRepository.savePositionsInDatabase(
            positionsToSave.map { position ->
                PositionDatabase(
                    localId = UUID.randomUUID().toString(),
                    date = position.date,
                    x = position.x,
                    y = position.y
                )
            }
        )
    }

}
