package com.laroy.adscientiamtest.data.repository

import com.laroy.adscientiamtest.domain.repository.ThemeRepository
import com.laroy.adscientiamtest.presentation.theme.AppTheme
import com.laroy.adscientiamtest.utils.AppDatastore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeRepositoryImpl @Inject constructor(
    private val datastore: AppDatastore
) : ThemeRepository {

    override fun getAppTheme(): Flow<AppTheme> {
        return datastore.getTheme
    }

    override suspend fun updateAppTheme(appTheme: AppTheme) {
        datastore.saveAppTheme(appTheme)
    }

}
