package com.demo.shaadi.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.shaadi.utils.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class ShaadiUser(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "profile_large")
    val profileLarge: String,

    @ColumnInfo(name = "profile_medium")
    val profileMedium: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "age")
    val age: Int?,

    @ColumnInfo(name = "location")
    val location: String,

    @ColumnInfo(name = "status")
    var status: Int,
)