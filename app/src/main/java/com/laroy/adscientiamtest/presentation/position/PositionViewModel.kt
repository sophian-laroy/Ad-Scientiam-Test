package com.laroy.adscientiamtest.presentation.position

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laroy.adscientiamtest.domain.usecase.GetPositionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PositionViewModel @Inject constructor(
    private val getPositionsUseCase: GetPositionsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PositionState())
    internal val state: StateFlow<PositionState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getPositionsUseCase().collect { positions ->
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(list = positions)
            }
        }
    }
}
