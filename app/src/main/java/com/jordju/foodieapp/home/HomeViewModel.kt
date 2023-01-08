package com.jordju.foodieapp.home

import androidx.lifecycle.*
import com.jordju.foodieapp.core.data.Resource
import com.jordju.foodieapp.core.domain.model.FoodList
import com.jordju.foodieapp.core.domain.usecase.GetFoodListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getFoodListUseCase: GetFoodListUseCase) : ViewModel() {
    private val foodList = MutableLiveData<Resource<FoodList>>()
    val getFoodListResult: LiveData<Resource<FoodList>>
        get() = foodList

    fun getFoodList() {
        viewModelScope.launch {
            getFoodListUseCase.execute().collect {
                foodList.value = it
            }
        }
    }
}