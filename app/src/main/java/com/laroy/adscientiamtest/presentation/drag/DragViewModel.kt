package com.laroy.adscientiamtest.presentation.drag

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laroy.adscientiamtest.domain.model.Position
import com.laroy.adscientiamtest.domain.usecase.SavePositionUseCase
import com.laroy.adscientiamtest.presentation.DragState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

const val MAX_SIZE_BEFORE_SAVING = 1000

@HiltViewModel
class DragViewModel @Inject constructor(
    private val savePositionUseCase: SavePositionUseCase
): ViewModel() {

    private val _state = MutableStateFlow(DragState())
    internal val state: StateFlow<DragState>
        get() = _state

    private val lastPositions = mutableListOf<Position>()

    fun onEvent(event: DragEvent) {
        when (event) {
            is DragEvent.PositionChanged -> {
                Log.d("Position", "Position list size ${lastPositions.size}")
                lastPositions.add(
                    Position(
                        Date().time,
                        event.x,
                        event.y
                    )
                )
                if (lastPositions.size >= MAX_SIZE_BEFORE_SAVING) {
                    viewModelScope.launch {
                        savePositionUseCase(lastPositions)
                    }
                    lastPositions.clear()
                }
            }
        }
    }
}
