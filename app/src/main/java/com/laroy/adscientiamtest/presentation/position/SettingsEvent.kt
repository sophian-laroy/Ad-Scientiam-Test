package com.laroy.adscientiamtest.presentation.position

sealed class SettingsEvent {
    object OnAppThemeDarkClicked: SettingsEvent()
    object OnAppThemeLightClicked: SettingsEvent()
    object OnAppThemeSystemClicked: SettingsEvent()
}
