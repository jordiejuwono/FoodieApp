package com.jordju.foodieapp.core.data.local.datasource

import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.data.local.room.FoodDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val foodDao: FoodDao) {
    fun getAllSavedFoods(): Flow<List<FoodEntity>> = foodDao.getAllSavedFoods()

    suspend fun insertFoodToFavorite(food: FoodEntity) = foodDao.insertFoodToFavorite(food)

    suspend fun deleteFoodFromFavorite(food: FoodEntity) = foodDao.deleteFoodFromFavorite(food)

    fun isFoodAlreadyExist(id: String): Flow<Boolean> = foodDao.isFoodAlreadyExist(id)
}