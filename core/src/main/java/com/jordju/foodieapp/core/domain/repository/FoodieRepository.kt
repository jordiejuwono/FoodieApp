package com.jordju.foodieapp.core.domain.repository

import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.domain.model.FoodDetails
import com.jordju.foodieapp.core.domain.model.FoodList
import kotlinx.coroutines.flow.Flow

interface FoodieRepository {
    fun getFoodList(query: String): Flow<Resource<FoodList>>

    fun getFoodDetails(detailId: String): Flow<Resource<FoodDetails>>

    fun getAllSavedFoods(): Flow<Resource<List<FoodEntity>>>

    suspend fun insertFoodToFavorite(food: FoodEntity)

    suspend fun deleteFoodFromFavorite(food: FoodEntity)

}