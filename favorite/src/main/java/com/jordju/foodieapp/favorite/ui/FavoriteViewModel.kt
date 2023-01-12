package com.jordju.foodieapp.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.domain.model.Food
import com.jordju.foodieapp.core.domain.usecase.FoodieUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val useCase: FoodieUseCase): ViewModel() {
    fun getAllSavedFoods() = useCase.getAllSavedFoods().asLiveData()

    fun deleteFoodFromFavorite(food: Food) {
        viewModelScope.launch {
            useCase.deleteSavedFood(food)
        }
    }
}