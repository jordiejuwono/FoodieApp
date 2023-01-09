package com.jordju.foodieapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jordju.foodieapp.core.domain.usecase.GetFoodDetailsUseCase
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val useCase: GetFoodDetailsUseCase) : ViewModel() {
    fun getFoodDetails(detailId: String) = useCase.execute(detailId).asLiveData()
}