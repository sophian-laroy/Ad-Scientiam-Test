package com.laroy.adscientiamtest.di

import com.laroy.adscientiamtest.data.database.dao.PositionDao
import com.laroy.adscientiamtest.data.source.PositionLocalDataSource
import com.laroy.adscientiamtest.data.source.PositionLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class SourceModule {

    @Singleton
    @Provides
    internal fun providePositionLocalDataSourceImpl(positionDao: PositionDao): PositionLocalDataSource {
        return PositionLocalDataSourceImpl(positionDao)
    }

}
