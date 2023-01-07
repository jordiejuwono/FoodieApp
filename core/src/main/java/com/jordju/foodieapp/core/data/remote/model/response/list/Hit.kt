package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class Hit(
    @SerializedName("_links")
    var links: Links?,
    @SerializedName("recipe")
    var recipe: Recipe?
)