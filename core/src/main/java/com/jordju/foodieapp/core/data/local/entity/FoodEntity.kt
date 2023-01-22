package com.jordju.foodieapp.core.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "foods")
@Parcelize
data class FoodEntity(
    @PrimaryKey
    @ColumnInfo(name = "recipeId")
    var recipeId: String,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "image")
    var image: String,
) : Parcelable