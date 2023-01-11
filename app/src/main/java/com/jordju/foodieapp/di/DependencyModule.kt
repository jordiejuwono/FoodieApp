package com.jordju.foodieapp.di

import com.jordju.foodieapp.core.domain.usecase.FoodieInteractor
import com.jordju.foodieapp.core.domain.usecase.FoodieUseCase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DependencyModule {
    @Binds
    @Singleton
    abstract fun provideFoodieUseCase(foodieInteractor: FoodieInteractor): FoodieUseCase
}