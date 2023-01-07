package com.jordju.foodieapp.core.data.remote.model.response.list


import com.google.gson.annotations.SerializedName

data class Digest(
    @SerializedName("daily")
    var daily: Double?,
    @SerializedName("hasRDI")
    var hasRDI: Boolean?,
    @SerializedName("label")
    var label: String?,
    @SerializedName("schemaOrgTag")
    var schemaOrgTag: String?,
    @SerializedName("sub")
    var sub: List<Sub?>?,
    @SerializedName("tag")
    var tag: String?,
    @SerializedName("total")
    var total: Double?,
    @SerializedName("unit")
    var unit: String?
)