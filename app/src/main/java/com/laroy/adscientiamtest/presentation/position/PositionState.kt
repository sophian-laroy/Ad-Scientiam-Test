package com.laroy.adscientiamtest.presentation.position

import com.laroy.adscientiamtest.domain.model.Position

data class PositionState(
    val isLoading: Boolean = true,
    val list: List<Position> = emptyList()
)
