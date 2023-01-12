package com.jordju.foodieapp.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM foods")
    fun getAllSavedFoods(): Flow<List<FoodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodToFavorite(food: FoodEntity)

    @Delete
    suspend fun deleteFoodFromFavorite(food: FoodEntity)

    @Query("SELECT EXISTS(SELECT * FROM foods WHERE recipeId=:id)")
    fun isFoodAlreadyExist(id: String): Flow<Boolean>

}