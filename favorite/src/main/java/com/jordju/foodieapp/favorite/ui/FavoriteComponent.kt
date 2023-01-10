package com.jordju.foodieapp.favorite.ui
//
//import android.content.Context
//import com.jordju.foodieapp.core.di.CoreComponent
//import com.jordju.foodieapp.core.di.RepositoryModule
//import com.jordju.foodieapp.di.AppModule
//import com.jordju.foodieapp.di.FavoriteModuleDependencies
//import dagger.BindsInstance
//import dagger.Component
//
//@AppScope
//@Component(dependencies = [FavoriteModuleDependencies::class, CoreComponent::class],)
//interface FavoriteComponent {
//
//    fun inject(fragment: FavoriteFragment)
//
//    @Component.Builder
//    interface Builder {
//        fun context(@BindsInstance context: Context): Builder
//        fun appDependencies(repositoryModule: FavoriteModuleDependencies): Builder
//        fun build(): FavoriteComponent
//    }
//
//}