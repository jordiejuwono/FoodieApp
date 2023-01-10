package com.jordju.foodieapp.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jordju.foodieapp.core.data.local.entity.FoodEntity

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
abstract class FoodsDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

}