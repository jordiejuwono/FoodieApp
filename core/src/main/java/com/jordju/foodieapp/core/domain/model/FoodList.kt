package com.jordju.foodieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodList(
    val count: Int,
    val hits: List<HitEntity>
): Parcelable

@Parcelize
data class HitEntity(
    val links: LinksEntity,
    val recipe: RecipeEntity,
): Parcelable

@Parcelize
data class LinksEntity(
    val self: SelfEntity,
): Parcelable

@Parcelize
data class SelfEntity(
    val href: String,
): Parcelable

@Parcelize
data class RecipeEntity(
    val uri: String,
    val calories: Double,
    val image: String,
    val ingredients: List<IngredientsEntity>,
    val label: String,
): Parcelable

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
): Parcelable