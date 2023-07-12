package com.laroy.adscientiamtest.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laroy.adscientiamtest.domain.usecase.AppThemeUseCase
import com.laroy.adscientiamtest.presentation.theme.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appThemeUseCase: AppThemeUseCase
): ViewModel() {

    private val _appTheme = MutableStateFlow(AppTheme.SYSTEM)
    internal val appTheme: StateFlow<AppTheme>
        get() = _appTheme

    init {
        getAppTheme()
    }

    private fun getAppTheme() {
        viewModelScope.launch {
            appThemeUseCase.get().collect { appTheme ->
                _appTheme.value = appTheme
            }
        }
    }
}