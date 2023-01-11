package com.jordju.foodieapp.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jordju.foodieapp.core.domain.usecase.FoodieUseCase
import com.jordju.foodieapp.core.domain.usecase.GetAllSavedFoodsUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val foodieUseCase: FoodieUseCase):
ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(foodieUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

}