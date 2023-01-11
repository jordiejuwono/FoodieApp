package com.jordju.foodieapp.di

import com.jordju.foodieapp.core.domain.usecase.FoodieUseCase
import javax.inject.Singleton

@Singleton
interface FavoriteModuleDependencies {
    fun foodieUseCase(): FoodieUseCase
}