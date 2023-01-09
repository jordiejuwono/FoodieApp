package com.jordju.foodieapp.searchresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jordju.foodieapp.core.domain.usecase.GetFoodListUseCase
import javax.inject.Inject

class SearchResultViewModel @Inject constructor(private val getFoodListUseCase: GetFoodListUseCase) : ViewModel() {
    fun getFoodList(query: String) = getFoodListUseCase.execute(query).asLiveData()
}