package com.jordju.foodieapp.home

import androidx.lifecycle.ViewModel
import com.jordju.foodieapp.core.domain.usecase.GetFoodListUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getFoodListUseCase: GetFoodListUseCase) : ViewModel() {
    fun getFoodList() = getFoodListUseCase.execute()
}