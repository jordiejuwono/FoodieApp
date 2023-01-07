package com.jordju.foodieapp.core.di

import android.content.Context
import com.jordju.foodieapp.core.data.remote.repository.FoodieRepositoryImpl
import com.jordju.foodieapp.core.domain.repository.FoodieRepository
import dagger.BindsInstance
import dagger.Component
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

}