package com.example.tesssst.model


import com.google.gson.annotations.SerializedName

data class TopResponseJsonGson(
    @SerializedName("data")
    val `data`: DataGson? = DataGson(),
    @SerializedName("kind")
    val kind: String? = ""
)