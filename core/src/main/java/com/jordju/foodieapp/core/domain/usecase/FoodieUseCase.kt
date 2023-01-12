package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

interface FoodieUseCase {
    fun getAllSavedFoods(): Flow<Resource<List<FoodEntity>>>
    suspend fun deleteSavedFood(foodEntity: FoodEntity)
}