package com.jordju.foodieapp.core.utils

import com.jordju.foodieapp.core.data.remote.model.response.detail.FoodDetailsResponse
import com.jordju.foodieapp.core.data.remote.model.response.list.*
import com.jordju.foodieapp.core.domain.model.*

object DataMapper {
    fun mapFoodDetailsToEntities(response: FoodDetailsResponse) =
        FoodDetails(
            links = mapLinksResponseToEntities(response.links),
            recipe = mapRecipeResponseToEntities(response.recipe)
        )

    fun mapFoodListToEntities(response: FoodListResponse) =
        FoodList(
            count = response.count ?: 0,
            hits = mapHitListToEntities(response.hits)
        )

    private fun mapHitListToEntities(response: List<Hit>?): List<HitEntity> {
        val hitList = ArrayList<HitEntity>()
        response?.map { input ->
            val hit = HitEntity(
                links = mapLinksResponseToEntities(input.links),
                recipe = mapRecipeResponseToEntities(input.recipe),
            )
            hitList.add(hit)
        }
        return hitList
    }


    private fun mapLinksResponseToEntities(response: Links?) =
        LinksEntity(
            self = mapSelfResponseToEntities(response?.self),
        )

    private fun mapSelfResponseToEntities(response: Self?) =
        SelfEntity(
            href = response?.href ?: "",
        )

    private fun mapRecipeResponseToEntities(response: Recipe?): RecipeEntity =
        RecipeEntity(
            uri = response?.uri ?: "",
            calories = response?.calories ?: 0.0,
            image = response?.image ?: "",
            ingredients = mapIngredientListToEntities(response?.ingredients),
            label = response?.label ?: ""
        )


    private fun mapIngredientListToEntities(responseList: List<Ingredient>?): List<IngredientsEntity> {
        val ingredientList = ArrayList<IngredientsEntity>()
        responseList?.map { input ->
            val ingredient = IngredientsEntity(
                food = input.food ?: "",
                foodCategory = input.foodCategory ?: "",
                foodId = input.foodId ?: "",
                image = input.image ?: "",
                measure = input.measure ?: "",
                quantity = input.quantity ?: 0.0,
                text = input.text ?: "",
                weight = input.weight ?: 0.0
            )
            ingredientList.add(ingredient)
        }
        return ingredientList
    }
}