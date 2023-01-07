package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import javax.inject.Inject

class GetFoodDetails @Inject constructor(private val foodieRepository: FoodieRepository) {
    fun execute(detailLink: String) = foodieRepository.getFoodDetails(detailLink)
}