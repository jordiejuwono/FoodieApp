package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import javax.inject.Inject

class GetFoodListUseCase @Inject constructor(private val foodieRepository: FoodieRepository) {
    fun execute(query: String) = foodieRepository.getFoodList(query)
}