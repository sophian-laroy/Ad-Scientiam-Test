package com.laroy.adscientiamtest.di

import com.laroy.adscientiamtest.data.repository.PositionRepositoryImpl
import com.laroy.adscientiamtest.data.source.PositionLocalDataSource
import com.laroy.adscientiamtest.domain.repository.PositionRepository
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
        positionLocalDataSource: PositionLocalDataSource
    ): PositionRepository {
        return PositionRepositoryImpl(
            positionLocalDataSource = positionLocalDataSource
        )
    }

}
