package com.application.submission_android_expert.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.application.submission_android_expert.core.data.source.remote.network.Result
import com.application.submission_android_expert.core.domain.model.Restaurant
import com.application.submission_android_expert.core.domain.usecase.RestaurantUseCase
import com.application.submission_android_expert.core.util.Event
import kotlinx.coroutines.launch

class DetailViewModel(private val restaurantUseCase: RestaurantUseCase): ViewModel() {
    private val _detailRestaurant = MutableLiveData<Restaurant>()
    val detailRestaurant: LiveData<Restaurant> = _detailRestaurant

    private val loadState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = loadState

    private val errorMsg = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> = errorMsg

    private val _isFav = MutableLiveData<Boolean>()
    val isFav: LiveData<Boolean> = _isFav

    fun getDetailRestaurantData(id: String) {
        loadState.value = true
        restaurantUseCase.getDetailRestaurant(id).asLiveData().observeForever { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        loadState.value = true
                    }
                    is Result.Success -> {
                        loadState.value = false
                        _detailRestaurant.value = result.data
                    }
                    is Result.Error -> {
                        loadState.value = false
                        errorMsg.value = result.errorMessage
                    }
                }
            }
        }
    }

    fun insertFavRestaurant() {
        viewModelScope.launch {
            _detailRestaurant.value?.let { restaurantUseCase.insertFavoriteRestaurant(it) }
        }
    }

    fun deleteFavRestaurant(restaurant: Restaurant) {
        viewModelScope.launch {
            restaurantUseCase.deleteFavoriteRestaurant(restaurant)
        }
    }

    fun isFavRestaurant(id: String){
        restaurantUseCase.isFavoriteRestaurant(id).asLiveData().observeForever { isFavorite ->
            _isFav.value = isFavorite
        }
    }
}