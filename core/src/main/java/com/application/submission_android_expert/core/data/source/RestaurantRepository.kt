package com.application.submission_android_expert.core.data.source

import com.application.submission_android_expert.core.data.source.local.LocalDataSource
import com.application.submission_android_expert.core.data.source.remote.RemoteDataSource
import com.application.submission_android_expert.core.data.source.remote.network.Result
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.application.submission_android_expert.core.domain.repository.IRestaurantRepository
import com.application.submission_android_expert.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RestaurantRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
): IRestaurantRepository {
    override fun getAllRestaurant(): Flow<Result<List<Restaurant>>> {
        return remoteDataSource.getAllRestaurant()
    }

    override fun getDetailRestaurant(id: String): Flow<Result<Restaurant>> {
        return remoteDataSource.getDetailRestaurant(id)
    }

    override fun getAllFavoriteRestaurant(): Flow<List<Restaurant>> {
        return localDataSource.getAllFavRestaurant().map { restaurantEntities ->
            DataMapper.restaurantEntityToRestaurantDomain(restaurantEntities)
        }
    }

    override suspend fun insertFavoriteRestaurant(restaurant: Restaurant) {
        val restaurantEntity = DataMapper.restaurantDomainToRestaurantEntity(restaurant)
        localDataSource.insertFavRestaurant(restaurantEntity)
    }

    override suspend fun deleteFavoriteRestaurant(restaurant: Restaurant) {
        val restaurantEntity = DataMapper.restaurantDomainToRestaurantEntity(restaurant)
        localDataSource.deleteFavRestaurant(restaurantEntity)
    }

    override fun isFavoriteRestaurant(id: String): Flow<Boolean> {
        return localDataSource.isFavRestaurant(id)
    }

    override fun getThemeSettings(): Flow<Boolean> {
        return localDataSource.getThemeSettings()
    }

    override suspend fun saveThemeSettings(isDarkMode: Boolean) {
        localDataSource.saveThemeSettings(isDarkMode)
    }
}