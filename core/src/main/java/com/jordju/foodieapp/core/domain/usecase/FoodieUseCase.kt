package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodieUseCase {
    fun getAllSavedFoods(): Flow<Resource<List<Food>>>
    suspend fun deleteSavedFood(food: Food)
}