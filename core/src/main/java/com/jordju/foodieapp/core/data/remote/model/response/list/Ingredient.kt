package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class Ingredient(
    @SerializedName("food")
    var food: String?,
    @SerializedName("foodCategory")
    var foodCategory: String?,
    @SerializedName("foodId")
    var foodId: String?,
    @SerializedName("image")
    var image: String?,
    @SerializedName("measure")
    var measure: String?,
    @SerializedName("quantity")
    var quantity: Double?,
    @SerializedName("text")
    var text: String?,
    @SerializedName("weight")
    var weight: Double?
)