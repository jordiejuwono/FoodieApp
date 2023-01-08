package com.jordju.foodieapp.di

import com.jordju.foodieapp.MainActivity
import com.jordju.foodieapp.core.di.CoreComponent
import com.jordju.foodieapp.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
}