package com.application.submission_android_expert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RestaurantResponse(

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("pictureId")
    val pictureId: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("categories")
    val categories: List<CategoriesItem>
)