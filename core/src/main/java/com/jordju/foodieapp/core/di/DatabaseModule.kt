package com.jordju.foodieapp.core.di

import android.content.Context
import androidx.room.Room
import com.jordju.foodieapp.core.data.local.room.FoodDao
import com.jordju.foodieapp.core.data.local.room.FoodsDatabase
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): FoodsDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("foodie".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            FoodsDatabase::class.java, "Foods.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideFoodDao(database: FoodsDatabase): FoodDao = database.foodDao()
}