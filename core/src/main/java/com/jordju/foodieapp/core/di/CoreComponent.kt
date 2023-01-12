package com.jordju.foodieapp.core.di

import android.content.Context
import com.jordju.foodieapp.core.data.repository.FoodieRepositoryImpl
import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import com.jordju.foodieapp.core.domain.usecase.FoodieUseCase
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): FoodieRepository
    fun provideUseCase(): FoodieUseCase

}