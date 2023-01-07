package com.jordju.foodieapp

import android.app.Application
import com.jordju.foodieapp.core.di.CoreComponent
import com.jordju.foodieapp.core.di.DaggerCoreComponent
import com.jordju.foodieapp.di.AppComponent
import com.jordju.foodieapp.di.DaggerAppComponent

open class MyApplication: Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

}