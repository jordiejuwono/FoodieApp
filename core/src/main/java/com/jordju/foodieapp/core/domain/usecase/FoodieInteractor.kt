package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.Food
import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodieInteractor @Inject constructor(private val foodieRepository: FoodieRepository): FoodieUseCase {
    override fun getAllSavedFoods(): Flow<Resource<List<Food>>> {
        return foodieRepository.getAllSavedFoods()
    }

    override suspend fun deleteSavedFood(food: Food) {
        return foodieRepository.deleteFoodFromFavorite(food)
    }

}