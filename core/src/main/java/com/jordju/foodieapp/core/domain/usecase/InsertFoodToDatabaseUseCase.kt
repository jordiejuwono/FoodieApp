package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.domain.model.Food
import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import javax.inject.Inject

class InsertFoodToDatabaseUseCase @Inject constructor(private val foodieRepository: FoodieRepository) {
    suspend fun execute(food: Food) = foodieRepository.insertFoodToFavorite(food)
}