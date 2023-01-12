package com.jordju.foodieapp.core.utils

import com.jordju.foodieapp.core.data.local.entity.FoodEntity
import com.jordju.foodieapp.core.data.remote.model.response.detail.FoodDetailsResponse
import com.jordju.foodieapp.core.data.remote.model.response.list.*
import com.jordju.foodieapp.core.domain.model.*

object DataMapper {
    fun mapFoodEntityListToFoodList(localData: List<FoodEntity>?): List<Food> {
        val foodList = ArrayList<Food>()
        localData?.map {
            val food = Food(
                recipeId = it.recipeId,
                name = it.name,
                image = it.image,
            )
            foodList.add(food)
        }
        return foodList
    }

    fun mapFoodToEntity(data: Food?): FoodEntity =
        FoodEntity(
            recipeId = data?.recipeId ?: "",
            name = data?.name ?: "",
            image = data?.image ?: "",
        )

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
            label = response?.label ?: "",
            totalTime = response?.totalTime?.toInt() ?: 0,
            totalNutrients = mapTotalNutrientsResponseToEntity(response?.totalNutrients)
        )

    private fun mapTotalNutrientsResponseToEntity(response: TotalNutrients?): TotalNutrientsEntity =
        TotalNutrientsEntity(
            energy = mapEnergyToEntity(response?.eNERCKCAL),
            fat = mapFatToEntity(response?.fAT),
            carbs = mapCarbsToEntity(response?.cHOCDF),
            sugar = mapSugarToEntity(response?.sUGAR),
            protein = mapProteinToEntity(response?.pROCNT),
            cholesterol = mapCholesterolToEntity(response?.cHOLE),
            calcium = mapCalciumToEntity(response?.cA),
        )

    private fun mapCalciumToEntity(
        response: CA?
    ): CalciumEntity {
        return CalciumEntity(
            label = response?.label ?: "",
            quantity = response?.quantity ?: 0.0,
            unit = response?.unit ?: "",
        )
    }

    private fun mapCholesterolToEntity(
        response: CHOLE?
    ): CholesterolEntity {
        return CholesterolEntity(
            label = response?.label ?: "",
            quantity = response?.quantity ?: 0.0,
            unit = response?.unit ?: "",
        )
    }

    private fun mapProteinToEntity(
        response: PROCNT?
    ): ProteinEntity {
        return ProteinEntity(
            label = response?.label ?: "",
            quantity = response?.quantity ?: 0.0,
            unit = response?.unit ?: "",
        )
    }

    private fun mapSugarToEntity(
        response: SUGAR?
    ): SugarEntity {
        return SugarEntity(
            label = response?.label ?: "",
            quantity = response?.quantity ?: 0.0,
            unit = response?.unit ?: "",
        )
    }

    private fun mapCarbsToEntity(
        response: CHOCDF?
    ): CarbsEntity {
        return CarbsEntity(
            label = response?.label ?: "",
            quantity = response?.quantity ?: 0.0,
            unit = response?.unit ?: "",
        )
    }

    private fun mapEnergyToEntity(
        response: ENERCKCAL?
    ): EnergyEntity {
        return EnergyEntity(
            label = response?.label ?: "",
            quantity = response?.quantity ?: 0.0,
            unit = response?.unit ?: "",
        )
    }

    private fun mapFatToEntity(
        response: FAT?
    ): FatEntity {
        return FatEntity(
            label = response?.label ?: "",
            quantity = response?.quantity ?: 0.0,
            unit = response?.unit ?: "",
        )
    }


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