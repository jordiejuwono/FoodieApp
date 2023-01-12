package com.jordju.foodieapp.core.di

import com.jordju.foodieapp.core.data.repository.FoodieRepositoryImpl
import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import com.jordju.foodieapp.core.domain.usecase.FoodieInteractor
import com.jordju.foodieapp.core.domain.usecase.FoodieUseCase
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

 @Binds
 abstract fun provideRepository(foodieRepository: FoodieRepositoryImpl): FoodieRepository

 @Binds
 abstract fun provideUseCase(foodieInteractor: FoodieInteractor): FoodieUseCase

}