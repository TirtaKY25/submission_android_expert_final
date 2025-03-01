package com.application.submission_android_expert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RestaurantDetailResponse(

	@field:SerializedName("restaurant")
	val restaurant: RestaurantResponse,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
