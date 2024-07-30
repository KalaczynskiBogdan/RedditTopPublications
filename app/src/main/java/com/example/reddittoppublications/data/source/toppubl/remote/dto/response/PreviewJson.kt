package com.example.reddittoppublications.data.source.toppubl.remote.dto.response


import com.google.gson.annotations.SerializedName

data class PreviewJson(
    @SerializedName("enabled")
    val enabled: Boolean? = false,
    @SerializedName("images")
    val images: List<ImageJson>? = listOf()
)