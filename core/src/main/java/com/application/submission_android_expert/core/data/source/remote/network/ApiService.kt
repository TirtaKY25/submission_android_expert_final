package com.application.submission_android_expert.core.data.source.remote.network

import com.application.submission_android_expert.core.data.source.remote.response.RestaurantDetailResponse
import com.application.submission_android_expert.core.data.source.remote.response.RestaurantListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("list")
    suspend fun getListRestaurant(): RestaurantListResponse

    @GET("detail/{id}")
    suspend fun getDetailRestaurant(@Path("id") id: String): RestaurantDetailResponse
}