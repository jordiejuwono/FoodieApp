package com.jordju.foodieapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.Food
import com.jordju.foodieapp.core.domain.model.FoodDetails
import com.jordju.foodieapp.core.domain.usecase.GetFoodDetailsUseCase
import com.jordju.foodieapp.core.domain.usecase.InsertFoodToDatabaseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getFoodDetails: GetFoodDetailsUseCase,
    private val insertFoodToFavorite: InsertFoodToDatabaseUseCase
) : ViewModel() {
    private val foodDetailsLiveData = MutableLiveData<Resource<FoodDetails>>()
    val foodDetails: LiveData<Resource<FoodDetails>>
        get() = foodDetailsLiveData

    fun getFoodDetails(detailId: String) {
        viewModelScope.launch {
            getFoodDetails.execute(detailId).collect {
                foodDetailsLiveData.value = it
            }
        }
    }

    fun insertFoodToFavorite(food: Food) {
        viewModelScope.launch {
            insertFoodToFavorite.execute(food)
        }
    }
}