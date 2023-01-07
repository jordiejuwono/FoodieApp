package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class Next(
    @SerializedName("href")
    var href: String?,
    @SerializedName("title")
    var title: String?
)