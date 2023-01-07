package com.jordju.foodieapp.di

import com.jordju.foodieapp.MainActivity
import com.jordju.foodieapp.core.di.CoreComponent
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = []
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)

}