package com.jordju.foodieapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.domain.usecase.GetFoodDetailsUseCase
import com.jordju.foodieapp.core.domain.usecase.InsertFoodToDatabaseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val getFoodDetails: GetFoodDetailsUseCase, private val insertFoodToFavorite: InsertFoodToDatabaseUseCase) : ViewModel() {
    fun getFoodDetails(detailId: String) = getFoodDetails.execute(detailId).asLiveData()

    fun insertFoodToFavorite(food: FoodEntity) {
        viewModelScope.launch {
            insertFoodToFavorite.execute(food)
        }
    }
}