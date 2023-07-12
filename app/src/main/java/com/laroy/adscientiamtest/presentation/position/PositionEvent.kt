package com.laroy.adscientiamtest.presentation.position

sealed class PositionEvent {
    object OnInverseOrderClicked: PositionEvent()
    object OnClearClicked: PositionEvent()
}
