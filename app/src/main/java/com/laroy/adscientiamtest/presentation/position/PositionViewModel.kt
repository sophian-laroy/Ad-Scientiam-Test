package com.laroy.adscientiamtest.presentation.position

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laroy.adscientiamtest.domain.model.PositionDir
import com.laroy.adscientiamtest.domain.usecase.ClearPositionsUseCase
import com.laroy.adscientiamtest.domain.usecase.GetPositionsUseCase
import com.laroy.adscientiamtest.domain.usecase.PositionDirUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PositionViewModel @Inject constructor(
    private val getPositionsUseCase: GetPositionsUseCase,
    private val positionDirUseCase: PositionDirUseCase,
    private val clearPositionsUseCase: ClearPositionsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PositionState())
    internal val state: StateFlow<PositionState>
        get() = _state

    init {
        viewModelScope.launch {
            getPositions()
        }
    }

    fun onEvent(event: PositionEvent) {
        when (event) {
            is PositionEvent.OnInverseOrderClicked -> {
                viewModelScope.launch {
                    positionDirUseCase.get().collect { positionDir ->
                        val newPositionDir = if (positionDir == PositionDir.DESC) {
                            PositionDir.ASC
                        } else {
                            PositionDir.DESC
                        }
                        positionDirUseCase.update(newPositionDir)
                        getPositions()
                    }
                }
            }
            is PositionEvent.OnClearClicked -> {
                viewModelScope.launch {
                    clearPositionsUseCase()
                    getPositions()
                }
            }
        }
    }

    private suspend fun getPositions() {
        _state.value = _state.value.copy(isLoading = true)
        positionDirUseCase.get().collect { positionDir ->
            getPositionsUseCase(positionDir).collect { positions ->
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(list = positions)
            }
        }
    }
}
