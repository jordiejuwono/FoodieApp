package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class FoodListResponse(
    @SerializedName("count")
    var count: Int?,
    @SerializedName("from")
    var from: Int?,
    @SerializedName("hits")
    var hits: List<Hit>?,
    @SerializedName("_links")
    var links: LinksX?,
    @SerializedName("to")
    var to: Int?
)