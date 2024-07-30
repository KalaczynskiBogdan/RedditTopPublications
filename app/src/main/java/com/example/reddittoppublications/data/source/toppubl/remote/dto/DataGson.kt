package com.example.tesssst.model


import com.google.gson.annotations.SerializedName

data class DataGson(
    @SerializedName("after")
    val after: String? = "",
    @SerializedName("before")
    val before: Any? = Any(),
    @SerializedName("children")
    val children: List<ChildrenGson>? = listOf(),
    @SerializedName("dist")
    val dist: Int? = 0,
    @SerializedName("geo_filter")
    val geoFilter: String? = "",
    @SerializedName("modhash")
    val modhash: Any? = Any()
)