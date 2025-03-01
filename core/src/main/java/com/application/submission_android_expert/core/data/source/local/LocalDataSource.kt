package com.application.submission_android_expert.core.data.source.local

import com.application.submission_android_expert.core.data.source.local.entity.RestaurantEntity
import com.application.submission_android_expert.core.data.source.local.preferences.SettingsPreferences
import com.application.submission_android_expert.core.data.source.local.room.RestaurantDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val restaurantDao: RestaurantDao, private val pref: SettingsPreferences) {

    suspend fun insertFavRestaurant(restaurant: RestaurantEntity) = restaurantDao.insertRestaurantFav(restaurant)

    suspend fun deleteFavRestaurant(restaurant: RestaurantEntity) =
        restaurantDao.deleteRestaurantFav(restaurant)

    fun getAllFavRestaurant(): Flow<List<RestaurantEntity>> = restaurantDao.getAllFavRestaurant()

    fun isFavRestaurant(id: String): Flow<Boolean> = restaurantDao.isFavRestaurant(id)

    fun getThemeSettings(): Flow<Boolean> = pref.getThemeSettings()

    suspend fun saveThemeSettings(isDarkMode: Boolean) = pref.saveThemeSettings(isDarkMode)
}