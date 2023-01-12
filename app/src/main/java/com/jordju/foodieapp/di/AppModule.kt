package com.jordju.foodieapp.di

import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import com.jordju.foodieapp.core.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideGetFoodListUseCase(foodieRepository: FoodieRepository) = GetFoodListUseCase(foodieRepository)

    @Provides
    fun provideGetFoodDetailsUseCase(foodieRepository: FoodieRepository) = GetFoodDetailsUseCase(foodieRepository)

    @Provides
    fun provideInsertFoodToFavorite(foodieRepository: FoodieRepository) = InsertFoodToDatabaseUseCase(foodieRepository)

    @Provides
    fun provideFoodieUseCase(foodieRepository: FoodieRepository) = FoodieInteractor(foodieRepository)

    @Provides
    fun isFoodAlreadyExist(foodieRepository: FoodieRepository) = IsFoodAlreadyExistUseCase(foodieRepository)

}