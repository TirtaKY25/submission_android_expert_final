package com.application.submission_android_expert.core.util

import com.application.submission_android_expert.core.data.source.local.entity.RestaurantEntity
import com.application.submission_android_expert.core.data.source.remote.response.RestaurantResponse
import com.application.submission_android_expert.core.data.source.remote.response.RestaurantsItemResponse
import com.application.submission_android_expert.core.domain.model.Categories
import com.application.submission_android_expert.core.domain.model.Restaurant

object DataMapper {

    fun restaurantItemResponseToRestaurantDomain(input: List<RestaurantsItemResponse>): List<Restaurant> =
        input.map {
            Restaurant(
                id = it.id,
                name = it.name,
                pictureId = it.pictureId,
                city = it.city,
                address = "",
                rating = it.rating,
                description = it.description,
                isFavorite = false,
                categories = listOf()
            )
        }

    fun restaurantDetailResponseToRestaurantDomain(input: RestaurantResponse): Restaurant {
        val newCategory = mutableListOf<Categories>()

        input.categories.forEach { category ->
            newCategory.add(Categories(category.name))
        }

        return Restaurant(
            id = input.id,
            name = input.name,
            pictureId = input.pictureId,
            city = input.city,
            address = input.address,
            rating = input.rating,
            description = input.description,
            isFavorite = false,
            categories = newCategory
        )
    }

    fun restaurantEntityToRestaurantDomain(input: List<RestaurantEntity>): List<Restaurant> =
        input.map {
            Restaurant(
                id = it.id,
                name = it.name,
                pictureId = it.pictureId,
                city = it.city,
                address = "",
                rating = it.rating,
                description = it.description,
                isFavorite = it.isFavorite,
                categories = listOf()
            )
        }

    fun restaurantDomainToRestaurantEntity(input: Restaurant): RestaurantEntity =
        RestaurantEntity(
            id = input.id,
            name = input.name,
            pictureId = input.pictureId,
            city = input.city,
            rating = input.rating,
            description = input.description,
            isFavorite = true,
        )

}