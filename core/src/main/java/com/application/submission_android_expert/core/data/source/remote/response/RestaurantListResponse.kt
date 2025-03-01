package com.application.submission_android_expert.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RestaurantListResponse(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("restaurants")
	val restaurants: List<RestaurantsItemResponse>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)
