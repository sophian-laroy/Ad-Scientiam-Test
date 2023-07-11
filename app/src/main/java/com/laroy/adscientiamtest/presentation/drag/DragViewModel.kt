package com.laroy.adscientiamtest.presentation.drag

import androidx.lifecycle.ViewModel
import com.laroy.adscientiamtest.presentation.DragState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DragViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(DragState())
    internal val state: StateFlow<DragState>
        get() = _state


    fun onEvent(event: DragEvent) {

    }
}
