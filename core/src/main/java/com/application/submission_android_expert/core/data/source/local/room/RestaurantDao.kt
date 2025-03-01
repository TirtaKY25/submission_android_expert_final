package com.application.submission_android_expert.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.application.submission_android_expert.core.data.source.local.entity.RestaurantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurantFav(restaurant: RestaurantEntity)

    @Delete
    suspend fun deleteRestaurantFav(restaurant: RestaurantEntity)

    @Query("SELECT * from restaurant WHERE isFavorite = 1")
    fun getAllFavRestaurant(): Flow<List<RestaurantEntity>>

    @Query("SELECT EXISTS (SELECT * FROM restaurant WHERE id = :id)")
    fun isFavRestaurant(id: String): Flow<Boolean>
}