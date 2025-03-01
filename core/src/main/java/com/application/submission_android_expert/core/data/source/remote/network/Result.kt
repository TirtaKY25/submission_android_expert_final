package com.application.submission_android_expert.core.data.source.remote.network

import com.application.submission_android_expert.core.util.Event

sealed class Result<out T> private constructor() {
    data object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: Event<String>) : Result<Nothing>()
}