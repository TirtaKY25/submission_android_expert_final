package com.application.submission_android_expert.core.domain.repository

import com.application.submission_android_expert.core.data.source.remote.network.Result
import com.application.submission_android_expert.core.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface IRestaurantRepository {
    fun getAllRestaurant(): Flow<Result<List<Restaurant>>>
    fun getDetailRestaurant(id: String): Flow<Result<Restaurant>>
    fun getAllFavoriteRestaurant(): Flow<List<Restaurant>>
    suspend fun insertFavoriteRestaurant(restaurant: Restaurant)
    suspend fun deleteFavoriteRestaurant(restaurant: Restaurant)
    fun isFavoriteRestaurant(id: String): Flow<Boolean>
    fun getThemeSettings(): Flow<Boolean>
    suspend fun saveThemeSettings(isDarkMode: Boolean)
}