package com.laroy.adscientiamtest.domain.usecase

import com.laroy.adscientiamtest.domain.repository.ThemeRepository
import com.laroy.adscientiamtest.presentation.theme.AppTheme
import javax.inject.Inject

class AppThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {

    fun get() = themeRepository.getAppTheme()

    suspend fun updateAppTheme(newAppTheme: AppTheme) {
        themeRepository.updateAppTheme(newAppTheme)
    }

}
