package com.application.submission_android_expert.core.domain.usecase

import com.application.submission_android_expert.core.data.source.remote.network.Result
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.application.submission_android_expert.core.domain.repository.IRestaurantRepository
import kotlinx.coroutines.flow.Flow

class RestaurantInteractor(private val restaurantRepository: IRestaurantRepository): RestaurantUseCase {

    override fun getAllRestaurant(): Flow<Result<List<Restaurant>>> {
        return restaurantRepository.getAllRestaurant()
    }

    override fun getAllFavoriteRestaurant(): Flow<List<Restaurant>> {
        return restaurantRepository.getAllFavoriteRestaurant()
    }

    override fun getDetailRestaurant(id: String): Flow<Result<Restaurant>> {
        return restaurantRepository.getDetailRestaurant(id)
    }

    override suspend fun insertFavoriteRestaurant(restaurant: Restaurant) {
        restaurantRepository.insertFavoriteRestaurant(restaurant)
    }

    override suspend fun deleteFavoriteRestaurant(restaurant: Restaurant) {
        restaurantRepository.deleteFavoriteRestaurant(restaurant)
    }

    override fun isFavoriteRestaurant(id: String): Flow<Boolean> {
        return restaurantRepository.isFavoriteRestaurant(id)
    }

    override fun getThemeSettings(): Flow<Boolean> {
        return restaurantRepository.getThemeSettings()
    }

    override suspend fun saveThemeSettings(isDarkMode: Boolean) {
        restaurantRepository.saveThemeSettings(isDarkMode)
    }
}