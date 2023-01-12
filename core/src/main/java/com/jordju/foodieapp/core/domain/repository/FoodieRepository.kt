package com.jordju.foodieapp.core.domain.repository

import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.Food
import com.jordju.foodieapp.core.domain.model.FoodDetails
import com.jordju.foodieapp.core.domain.model.FoodList
import kotlinx.coroutines.flow.Flow

interface FoodieRepository {
    fun getFoodList(query: String): Flow<Resource<FoodList>>

    fun getFoodDetails(detailId: String): Flow<Resource<FoodDetails>>

    fun getAllSavedFoods(): Flow<Resource<List<Food>>>

    suspend fun insertFoodToFavorite(food: Food)

    suspend fun deleteFoodFromFavorite(food: Food)

    fun isFoodAlreadyExist(id: String): Flow<Boolean>

}