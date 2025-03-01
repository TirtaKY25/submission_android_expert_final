package com.application.submission_android_expert.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.application.submission_android_expert.core.data.source.remote.network.Result
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.application.submission_android_expert.core.domain.usecase.RestaurantUseCase
import com.application.submission_android_expert.core.util.Event

class HomeViewModel(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {

    private val _restaurants = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>> = _restaurants

    private val loadState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = loadState

    private val errorMsg = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = errorMsg

    init {
        getAllRestaurantsData()
    }

    fun getAllRestaurantsData() {
        loadState.value = true
        restaurantUseCase.getAllRestaurant().asLiveData().observeForever { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        loadState.value = true
                    }
                    is Result.Success -> {
                        loadState.value = false
                        _restaurants.value = result.data
                    }
                    is Result.Error -> {
                        loadState.value = false
                        errorMsg.value = result.errorMessage
                    }
                }
            }
        }
    }
}