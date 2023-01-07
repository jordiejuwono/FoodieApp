package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("self")
    var self: Self?
)