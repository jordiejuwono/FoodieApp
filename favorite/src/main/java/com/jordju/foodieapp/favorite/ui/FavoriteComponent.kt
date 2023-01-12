package com.jordju.foodieapp.favorite.ui

import com.jordju.foodieapp.di.AppComponent
import dagger.Component

@AppScope
@Component(
    dependencies = [AppComponent::class],
)
interface FavoriteComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): FavoriteComponent
    }

    fun inject(fragment: FavoriteFragment)

}