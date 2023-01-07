package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class CHOCDF(
    @SerializedName("label")
    var label: String?,
    @SerializedName("quantity")
    var quantity: Double?,
    @SerializedName("unit")
    var unit: String?
)