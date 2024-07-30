package com.example.tesssst.model


import com.google.gson.annotations.SerializedName

data class SourceGson(
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: Int? = null
)