package com.jordju.foodieapp.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jordju.foodieapp.core.domain.usecase.GetAllSavedFoodsUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(private val useCase: GetAllSavedFoodsUseCase): ViewModel() {
    fun getAllSavedFoods() = useCase.execute().asLiveData()
}