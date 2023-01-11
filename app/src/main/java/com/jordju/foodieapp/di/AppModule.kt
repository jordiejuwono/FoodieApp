package com.jordju.foodieapp.di

import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import com.jordju.foodieapp.core.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideGetFoodListUseCase(foodieRepository: FoodieRepository) = GetFoodListUseCase(foodieRepository)

    @Provides
    fun provideGetFoodDetailsUseCase(foodieRepository: FoodieRepository) = GetFoodDetailsUseCase(foodieRepository)

    @Provides
    fun provideInsertFoodToFavorite(foodieRepository: FoodieRepository) = InsertFoodToDatabaseUseCase(foodieRepository)

}