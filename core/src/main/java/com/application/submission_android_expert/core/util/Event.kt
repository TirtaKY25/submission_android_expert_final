package com.application.submission_android_expert.core.util

open class Event<out T>(private val data: T) {
    private var hasDisplayed = false

    fun getDataIfNotDisplayed(): T? {
        return if(hasDisplayed){
            null
        } else {
            hasDisplayed = true
            data
        }
    }
}