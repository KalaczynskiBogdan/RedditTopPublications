package com.example.tesssst.model


import com.google.gson.annotations.SerializedName

data class PreviewGson(
    @SerializedName("enabled")
    val enabled: Boolean? = false,
    @SerializedName("images")
    val images: List<ImageGson>? = listOf()
)