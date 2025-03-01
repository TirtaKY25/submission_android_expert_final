package com.application.submission_android_expert.di

import com.application.submission_android_expert.core.domain.usecase.RestaurantInteractor
import com.application.submission_android_expert.core.domain.usecase.RestaurantUseCase
import com.application.submission_android_expert.ui.detail.DetailViewModel
import com.application.submission_android_expert.ui.home.HomeViewModel
import com.application.submission_android_expert.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RestaurantUseCase> { RestaurantInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}