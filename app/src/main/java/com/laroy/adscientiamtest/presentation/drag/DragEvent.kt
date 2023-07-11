package com.laroy.adscientiamtest.presentation.drag

sealed class DragEvent {
    data class PositionChanged(val x: Int, val y: Int) : DragEvent()
    object OnScreenPaused: DragEvent()
}
