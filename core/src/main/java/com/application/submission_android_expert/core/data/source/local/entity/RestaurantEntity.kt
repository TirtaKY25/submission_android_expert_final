package com.application.submission_android_expert.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
class RestaurantEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "pictureId")
    val pictureId: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)