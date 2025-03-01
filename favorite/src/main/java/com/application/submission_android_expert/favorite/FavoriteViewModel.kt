package com.application.submission_android_expert.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.application.submission_android_expert.core.domain.usecase.RestaurantUseCase

class FavoriteViewModel(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = _restaurants

    init {
        getAllFavRestaurantsData()
    }

    private fun getAllFavRestaurantsData() {
        restaurantUseCase.getAllFavoriteRestaurant().asLiveData().observeForever { data ->
            _restaurants.value = data
        }
    }

}