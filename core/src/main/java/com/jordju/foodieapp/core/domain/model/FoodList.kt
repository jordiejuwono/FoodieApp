package com.jordju.foodieapp.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.jordju.foodieapp.core.data.remote.model.response.list.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodList(
    val count: Int,
    val hits: List<HitEntity>
) : Parcelable

@Parcelize
data class HitEntity(
    val links: LinksEntity,
    val recipe: RecipeEntity,
) : Parcelable

@Parcelize
data class LinksEntity(
    val self: SelfEntity,
) : Parcelable

@Parcelize
data class SelfEntity(
    val href: String,
) : Parcelable

@Parcelize
data class RecipeEntity(
    val uri: String,
    val calories: Double,
    val image: String,
    val ingredients: List<IngredientsEntity>,
    val label: String,
    val totalTime: Int,
    val totalNutrients: TotalNutrientsEntity
) : Parcelable

@Parcelize
data class TotalNutrientsEntity(
    val energy: EnergyEntity,
    val fat: FatEntity,
    val carbs: CarbsEntity,
    val sugar: SugarEntity,
    val protein: ProteinEntity,
    val cholesterol: CholesterolEntity,
    val calcium: CalciumEntity,
) : Parcelable

@Parcelize
data class EnergyEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class FatEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class CarbsEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class SugarEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class ProteinEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class CholesterolEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class CalciumEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class IngredientsEntity(
    val food: String,
    val foodCategory: String,
    val foodId: String,
    val image: String,
    val measure: String,
    val quantity: Double,
    val text: String,
    val weight: Double
) : Parcelable