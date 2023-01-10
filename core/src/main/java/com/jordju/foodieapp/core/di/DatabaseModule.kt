package com.jordju.foodieapp.core.di

import android.content.Context
import androidx.room.Room
import com.jordju.foodieapp.core.data.local.room.FoodDao
import com.jordju.foodieapp.core.data.local.room.FoodsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): FoodsDatabase = Room.databaseBuilder(
        context,
        FoodsDatabase::class.java, "Foods.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideFoodDao(database: FoodsDatabase): FoodDao = database.foodDao()
}