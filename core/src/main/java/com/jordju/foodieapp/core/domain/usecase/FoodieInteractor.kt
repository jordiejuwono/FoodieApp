package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.domain.model.Food
import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import com.jordju.foodieapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FoodieInteractor @Inject constructor(private val foodieRepository: FoodieRepository): FoodieUseCase {
    override fun getAllSavedFoods(): Flow<Resource<List<Food>>> {
        return foodieRepository.getAllSavedFoods()
    }

    override suspend fun deleteSavedFood(food: Food) {
        return foodieRepository.deleteFoodFromFavorite(food)
    }
}