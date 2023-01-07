package com.jordju.foodieapp.core.domain.model

data class FoodList(
    val count: Int,
    val hits: List<HitEntity>
)

data class HitEntity(
    val links: LinksEntity,
    val recipe: RecipeEntity,
)

data class LinksEntity(
    val self: SelfEntity,
)

data class SelfEntity(
    val href: String,
)

data class RecipeEntity(
    val uri: String,
    val calories: Double,
    val image: String,
    val ingredients: List<IngredientsEntity>,
    val label: String,
)

data class IngredientsEntity(
    val food: String,
    val foodCategory: String,
    val foodId: String,
    val image: String,
    val measure: String,
    val quantity: Double,
    val text: String,
    val weight: Double
)