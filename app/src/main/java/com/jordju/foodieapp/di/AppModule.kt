package com.jordju.foodieapp.di

import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import com.jordju.foodieapp.core.domain.usecase.GetFoodListUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideGetFoodListUseCase(foodieRepository: FoodieRepository) = GetFoodListUseCase(foodieRepository)

}