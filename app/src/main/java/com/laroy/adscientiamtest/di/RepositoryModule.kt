package com.laroy.adscientiamtest.di

import com.laroy.adscientiamtest.data.repository.PositionRepositoryImpl
import com.laroy.adscientiamtest.data.repository.ThemeRepositoryImpl
import com.laroy.adscientiamtest.data.source.PositionLocalDataSource
import com.laroy.adscientiamtest.domain.repository.PositionRepository
import com.laroy.adscientiamtest.domain.repository.ThemeRepository
import com.laroy.adscientiamtest.utils.AppDatastore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RepositoryModule {

    @Singleton
    @Provides
    internal fun providePositionRepositoryImpl(
        positionLocalDataSource: PositionLocalDataSource,
        datastore: AppDatastore
    ): PositionRepository {
        return PositionRepositoryImpl(
            positionLocalDataSource = positionLocalDataSource,
            datastore = datastore
        )
    }

    @Singleton
    @Provides
    internal fun provideThemeRepositoryImpl(
        datastore: AppDatastore
    ): ThemeRepository {
        return ThemeRepositoryImpl(
            datastore = datastore
        )
    }

}
