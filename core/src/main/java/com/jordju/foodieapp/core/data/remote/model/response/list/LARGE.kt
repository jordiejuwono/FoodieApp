package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class LARGE(
    @SerializedName("height")
    var height: Int?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int?
)