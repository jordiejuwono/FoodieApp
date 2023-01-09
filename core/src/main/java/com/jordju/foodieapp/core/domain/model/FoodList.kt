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
    val eNERCKCAL: eNERCKCALEntity,
    val fAT: fATEntity,
    val cHOCDF: cHOCDFEntity,
    val sUGAR: sUGAREntity,
    val pROCNT: pROCNTEntity,
    val cHOLE: cHOLEEntity,
    val cA: cAEntity,
) : Parcelable

@Parcelize
data class eNERCKCALEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class fATEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class cHOCDFEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class cHOCDFNetEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class sUGAREntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class pROCNTEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class cHOLEEntity(
    var label: String,
    var quantity: Double,
    var unit: String
) : Parcelable

@Parcelize
data class cAEntity(
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