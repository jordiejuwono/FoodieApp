package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("calories")
    var calories: Double?,
    @SerializedName("cautions")
    var cautions: List<String>?,
    @SerializedName("cuisineType")
    var cuisineType: List<String>?,
    @SerializedName("dietLabels")
    var dietLabels: List<String>?,
    @SerializedName("digest")
    var digest: List<Digest>?,
    @SerializedName("dishType")
    var dishType: List<String>?,
    @SerializedName("healthLabels")
    var healthLabels: List<String>?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("images")
    var images: Images?,
    @SerializedName("ingredientLines")
    var ingredientLines: List<String>?,
    @SerializedName("ingredients")
    var ingredients: List<Ingredient>?,
    @SerializedName("label")
    var label: String?,
    @SerializedName("mealType")
    var mealType: List<String>?,
    @SerializedName("shareAs")
    var shareAs: String?,
    @SerializedName("source")
    var source: String?,
    @SerializedName("totalDaily")
    var totalDaily: TotalDaily?,
    @SerializedName("totalNutrients")
    var totalNutrients: TotalNutrients?,
    @SerializedName("totalTime")
    var totalTime: Double?,
    @SerializedName("totalWeight")
    var totalWeight: Double?,
    @SerializedName("uri")
    var uri: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("yield")
    var yield: Double?
)