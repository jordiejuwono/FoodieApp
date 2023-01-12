package com.jordju.foodieapp.core.domain.usecase

import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsFoodAlreadyExistUseCase @Inject constructor(private val foodieRepository: FoodieRepository) {
    fun execute(id: String): Flow<Boolean> = foodieRepository.isFoodAlreadyExist(id)
}