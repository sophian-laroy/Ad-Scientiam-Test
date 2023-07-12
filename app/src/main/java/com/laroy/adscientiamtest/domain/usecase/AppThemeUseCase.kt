package com.laroy.adscientiamtest.domain.usecase

import com.laroy.adscientiamtest.domain.model.Position
import com.laroy.adscientiamtest.domain.model.PositionDir
import com.laroy.adscientiamtest.domain.repository.PositionRepository
import com.laroy.adscientiamtest.domain.repository.ThemeRepository
import com.laroy.adscientiamtest.presentation.theme.AppTheme
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppThemeUseCase @Inject constructor(
    private val themeRepository: ThemeRepository
) {

    fun get() = themeRepository.getAppTheme()

    suspend fun updateAppTheme(newAppTheme: AppTheme) {
        themeRepository.updateAppTheme(newAppTheme)
    }

}
