package com.example.reddittoppublications.data.source.toppubl.remote.dto.response


import com.google.gson.annotations.SerializedName

data class DataJson(
    @SerializedName("after")
    val after: String? = "",
    @SerializedName("before")
    val before: Any? = Any(),
    @SerializedName("children")
    val children: List<ChildrenJson>? = listOf(),
    @SerializedName("dist")
    val dist: Int? = 0,
    @SerializedName("geo_filter")
    val geoFilter: String? = "",
    @SerializedName("modhash")
    val modhash: Any? = Any()
)