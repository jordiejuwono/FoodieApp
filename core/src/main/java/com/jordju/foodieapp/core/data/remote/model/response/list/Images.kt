package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("LARGE")
    var lARGE: LARGE?,
    @SerializedName("REGULAR")
    var rEGULAR: REGULAR?,
    @SerializedName("SMALL")
    var sMALL: SMALL?,
    @SerializedName("THUMBNAIL")
    var tHUMBNAIL: THUMBNAIL?
)