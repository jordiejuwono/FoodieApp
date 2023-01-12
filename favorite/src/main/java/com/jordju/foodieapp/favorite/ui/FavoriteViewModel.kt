package com.jordju.foodieapp.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jordju.foodieapp.core.domain.usecase.FoodieUseCase
import com.jordju.foodieapp.core.domain.usecase.GetAllSavedFoodsUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val useCase: FoodieUseCase): ViewModel() {
    fun getAllSavedFoods() = useCase.getAllSavedFoods().asLiveData()
}