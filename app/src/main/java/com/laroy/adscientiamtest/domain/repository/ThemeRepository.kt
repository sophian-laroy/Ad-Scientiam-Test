package com.laroy.adscientiamtest.domain.repository

import com.laroy.adscientiamtest.presentation.theme.AppTheme
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {

    fun getAppTheme(): Flow<AppTheme>

    suspend fun updateAppTheme(appTheme: AppTheme)

}
