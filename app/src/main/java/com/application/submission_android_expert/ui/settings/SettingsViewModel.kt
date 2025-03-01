package com.application.submission_android_expert.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.application.submission_android_expert.core.domain.usecase.RestaurantUseCase
import kotlinx.coroutines.launch

class SettingsViewModel(private val restaurantUseCase: RestaurantUseCase) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> = restaurantUseCase.getThemeSettings().asLiveData()
    fun saveThemeSettings(isDarkMode: Boolean) {
        viewModelScope.launch {
            restaurantUseCase.saveThemeSettings(isDarkMode)
        }
    }

}