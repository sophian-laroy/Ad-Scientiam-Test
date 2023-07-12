package com.laroy.adscientiamtest

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.laroy.adscientiamtest.data.database.AppDatabase
import com.laroy.adscientiamtest.data.database.dao.PositionDao
import com.laroy.adscientiamtest.data.database.entity.PositionDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    private val context: Context by lazy { ApplicationProvider.getApplicationContext() }

    private lateinit var appDatabase: AppDatabase
    private lateinit var positionDao: PositionDao

    private val firstPositionToAdd =
        PositionDatabase(
            localId = "uuid1",
            date = 1111111111L,
            x = 100,
            y = 100
        )

    private val secondPositionToAdd =
        PositionDatabase(
            localId = "uuid2",
            date = 2222222222,
            x = 200,
            y = 200
        )

    @Before
    fun setup() {
        appDatabase =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries().build()
        positionDao = appDatabase.positionDao()
    }

    @After
    fun cleanup() {
        appDatabase.close()
    }

    @Test
    fun testInstantiations() = runTest {
        Truth.assertWithMessage("appDatabase instantiation: ").that(appDatabase)
            .isNotNull()
        Truth.assertWithMessage("positionDao instantiation: ").that(positionDao).isNotNull()
    }

    @Test
    fun testAddSingle() = runTest {
        positionDao.save(firstPositionToAdd)
        var list = positionDao.getAll(true)
        Truth.assertWithMessage("List size").that(list.size == 1).isTrue()
        Truth.assertWithMessage("First position").that(list[0] == firstPositionToAdd).isTrue()
        Truth.assertWithMessage("First position").that(list[0] == secondPositionToAdd).isFalse()

        positionDao.save(secondPositionToAdd)
        list = positionDao.getAll(true)
        Truth.assertWithMessage("List size").that(list.size == 2).isTrue()
        Truth.assertWithMessage("First position").that(list[0] == firstPositionToAdd).isTrue()
        Truth.assertWithMessage("First position").that(list[0] == secondPositionToAdd).isFalse()
        Truth.assertWithMessage("Second position").that(list[1] == firstPositionToAdd).isFalse()
        Truth.assertWithMessage("Second position").that(list[1] == secondPositionToAdd).isTrue()
    }

    @Test
    fun testAddMultiple() = runTest {
        positionDao.save(firstPositionToAdd, secondPositionToAdd)
        val list = positionDao.getAll(true)
        Truth.assertWithMessage("List size").that(list.size == 1).isFalse()
        Truth.assertWithMessage("List size").that(list.size == 2).isTrue()
        Truth.assertWithMessage("First position").that(list[0] == firstPositionToAdd).isTrue()
        Truth.assertWithMessage("First position").that(list[0] == secondPositionToAdd).isFalse()
        Truth.assertWithMessage("Second position").that(list[1] == firstPositionToAdd).isFalse()
        Truth.assertWithMessage("Second position").that(list[1] == secondPositionToAdd).isTrue()
    }

    @Test
    fun testClear() = runTest {
        positionDao.save(firstPositionToAdd)
        positionDao.save(secondPositionToAdd)
        var list = positionDao.getAll(true)
        Truth.assertWithMessage("List size").that(list.size == 2).isTrue()

        positionDao.deleteAll()
        list = positionDao.getAll(true)
        Truth.assertWithMessage("List size").that(list.isEmpty()).isTrue()
    }

    @Test
    fun testOrder() = runTest {
        positionDao.save(firstPositionToAdd)
        positionDao.save(secondPositionToAdd)
        var list = positionDao.getAll(true)
        Truth.assertWithMessage("First position").that(list[0] == firstPositionToAdd).isTrue()
        Truth.assertWithMessage("First position").that(list[0] == secondPositionToAdd).isFalse()
        Truth.assertWithMessage("Second position").that(list[1] == firstPositionToAdd).isFalse()
        Truth.assertWithMessage("Second position").that(list[1] == secondPositionToAdd).isTrue()

        list = positionDao.getAll(false)
        Truth.assertWithMessage("First position").that(list[0] == secondPositionToAdd).isTrue()
        Truth.assertWithMessage("First position").that(list[0] == firstPositionToAdd).isFalse()
        Truth.assertWithMessage("Second position").that(list[1] == secondPositionToAdd).isFalse()
        Truth.assertWithMessage("Second position").that(list[1] == firstPositionToAdd).isTrue()

    }

}