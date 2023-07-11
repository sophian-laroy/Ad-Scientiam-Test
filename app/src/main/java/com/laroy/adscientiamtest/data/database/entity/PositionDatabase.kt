package com.laroy.adscientiamtest.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Position")
data class PositionDatabase(

    @PrimaryKey
    @ColumnInfo(name = "localId")
    val localId: String,

    @ColumnInfo(name = "date")
    val date: Long,

    @ColumnInfo(name = "x")
    val x: Int,

    @ColumnInfo(name = "y")
    val y: Int

)
