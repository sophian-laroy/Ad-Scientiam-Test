package com.laroy.adscientiamtest.utils

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.laroy.adscientiamtest.domain.model.PositionDir
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDatastore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("AdScentiamTest")
        private val ORDER_DIR_KEY = stringPreferencesKey("orderDir")
    }

    val getPositionDir: Flow<PositionDir> = context.dataStore.data.map { preferences ->
        return@map try {
            PositionDir.valueOf(preferences[ORDER_DIR_KEY] ?: "")
        } catch (e: Exception) {
            Log.e("AppDatastore", e.message ?: "")
            PositionDir.ASC
        }
    }

    suspend fun saveOrderDir(positionDir: PositionDir) {
        context.dataStore.edit { preferences ->
            preferences[ORDER_DIR_KEY] = positionDir.name
        }
    }
}