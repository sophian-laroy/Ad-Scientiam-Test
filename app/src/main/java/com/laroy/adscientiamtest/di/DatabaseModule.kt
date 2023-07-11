package com.laroy.adscientiamtest.di

import android.content.Context
import com.laroy.adscientiamtest.data.database.AppDatabase
import com.laroy.adscientiamtest.data.database.dao.PositionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.createDatabase(appContext)
    }

    @Provides
    fun providePositionDao(appDatabase: AppDatabase): PositionDao {
        return appDatabase.positionDao()
    }

}