package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodieInteractor @Inject constructor(private val foodieRepository: FoodieRepository): FoodieUseCase {
    override fun getAllSavedFoods(): Flow<Resource<List<FoodEntity>>> {
        return foodieRepository.getAllSavedFoods()
    }
}