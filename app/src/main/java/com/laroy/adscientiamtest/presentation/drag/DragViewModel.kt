package com.laroy.adscientiamtest.presentation.drag

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laroy.adscientiamtest.domain.model.Position
import com.laroy.adscientiamtest.domain.usecase.SavePositionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

const val MAX_SIZE_BEFORE_SAVING = 100

@HiltViewModel
class DragViewModel @Inject constructor(
    private val savePositionUseCase: SavePositionUseCase
): ViewModel() {

    // We save in database the last positions to avoid to save at each new position
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
                    savePositions()
                }
            }
            DragEvent.OnScreenPaused -> {
                savePositions()
            }
        }
    }

    private fun savePositions() {
        viewModelScope.launch {
            savePositionUseCase(lastPositions)
        }
        lastPositions.clear()
    }
}
