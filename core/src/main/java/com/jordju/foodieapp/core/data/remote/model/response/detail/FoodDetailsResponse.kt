package com.jordju.foodieapp.core.data.remote.model.response.detail


import com.google.gson.annotations.SerializedName
import com.jordju.foodieapp.core.data.remote.model.response.list.Links
import com.jordju.foodieapp.core.data.remote.model.response.list.Recipe

data class FoodDetailsResponse(
    @SerializedName("_links")
    var links: Links?,
    @SerializedName("recipe")
    var recipe: Recipe?
)