package com.jordju.foodieapp.core.domain.repository

import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.FoodDetails
import com.jordju.foodieapp.core.domain.model.FoodList
import kotlinx.coroutines.flow.Flow

interface FoodieRepository {
    fun getFoodList(query: String): Flow<Resource<FoodList>>

    fun getFoodDetails(detailId: String): Flow<Resource<FoodDetails>>
}