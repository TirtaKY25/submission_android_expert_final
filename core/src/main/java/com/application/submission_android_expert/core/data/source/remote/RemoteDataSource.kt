package com.application.submission_android_expert.core.data.source.remote

import com.application.submission_android_expert.core.data.source.remote.network.ApiService
import com.application.submission_android_expert.core.data.source.remote.network.Result
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.application.submission_android_expert.core.util.DataMapper
import com.application.submission_android_expert.core.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getAllRestaurant(): Flow<Result<List<Restaurant>>> {
        return flow {
            try {
                val response = apiService.getListRestaurant()
                val restaurants = response.restaurants
                val restaurantsData = DataMapper.restaurantItemResponseToRestaurantDomain(restaurants)
                emit(Result.Success(restaurantsData))
            } catch (e: Exception) {
                val errorMessage = when(e) {
                    is java.net.UnknownHostException -> "No internet connection"
                    is retrofit2.HttpException -> "Request Failed With Error ${e.code()}"
                    else -> "Unknown Error"
                }
                emit(Result.Error(Event(errorMessage)))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailRestaurant(id: String): Flow<Result<Restaurant>> {
        return flow {
            try {
                val response = apiService.getDetailRestaurant(id)
                val restaurant = response.restaurant
                val detailRestaurant = DataMapper.restaurantDetailResponseToRestaurantDomain(restaurant)
                emit(Result.Success(detailRestaurant))

            } catch (e: Exception) {
                val errorMessage = when(e) {
                    is java.net.UnknownHostException -> "No internet connection"
                    is retrofit2.HttpException -> "Request Failed With Error ${e.code()}"
                    else -> "Unknown Error"
                }
                emit(Result.Error(Event(errorMessage)))
            }
        }.flowOn(Dispatchers.IO)
    }

}