package com.application.submission_android_expert.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.application.submission_android_expert.core.data.source.local.entity.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase: RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

}