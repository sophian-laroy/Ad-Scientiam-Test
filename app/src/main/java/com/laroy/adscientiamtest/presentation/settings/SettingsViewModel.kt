package com.laroy.adscientiamtest.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laroy.adscientiamtest.R
import com.laroy.adscientiamtest.domain.usecase.AppThemeUseCase
import com.laroy.adscientiamtest.presentation.position.SettingsEvent
import com.laroy.adscientiamtest.presentation.theme.AppTheme
import com.laroy.adscientiamtest.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appThemeUseCase: AppThemeUseCase
): ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    internal val state: StateFlow<SettingsState>
        get() = _state

    init {
        getAppTheme()
    }

    private fun getAppTheme() {
        viewModelScope.launch {
            appThemeUseCase.get().collect { appTheme ->
                _state.value = _state.value.copy(appTheme = getAppThemeString(appTheme))
            }
        }
    }

    private fun getAppThemeString(appTheme: AppTheme): UiText {
        return when (appTheme) {
            AppTheme.SYSTEM -> UiText.StringResource(R.string.app_theme_system)
            AppTheme.DARK -> UiText.StringResource(R.string.app_theme_dark)
            AppTheme.LIGHT -> UiText.StringResource(R.string.app_theme_light)
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            SettingsEvent.OnAppThemeDarkClicked -> {
                viewModelScope.launch {
                    appThemeUseCase.updateAppTheme(AppTheme.DARK)
                }
            }
            SettingsEvent.OnAppThemeLightClicked -> {
                viewModelScope.launch {
                    appThemeUseCase.updateAppTheme(AppTheme.LIGHT)
                }
            }
            SettingsEvent.OnAppThemeSystemClicked -> {
                viewModelScope.launch {
                    appThemeUseCase.updateAppTheme(AppTheme.SYSTEM)
                }
            }
        }
    }
}