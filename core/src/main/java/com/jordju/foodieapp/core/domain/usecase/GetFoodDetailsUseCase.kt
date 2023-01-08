package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import javax.inject.Inject

class GetFoodDetailsUseCase @Inject constructor(private val foodieRepository: FoodieRepository) {
    fun execute(detailId: String) = foodieRepository.getFoodDetails(detailId)
}