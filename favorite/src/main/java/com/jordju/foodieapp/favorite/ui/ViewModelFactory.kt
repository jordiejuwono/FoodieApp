package com.jordju.foodieapp.favorite.ui

import androidx.lifecycle.ViewModelProvider
import com.jordju.foodieapp.core.domain.usecase.GetAllSavedFoodsUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val getAllSavedFoodsUseCase: GetAllSavedFoodsUseCase):
ViewModelProvider.NewInstanceFactory() {
}