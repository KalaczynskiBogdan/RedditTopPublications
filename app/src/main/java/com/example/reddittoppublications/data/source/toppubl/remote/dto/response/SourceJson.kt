package com.example.reddittoppublications.data.source.toppubl.remote.dto.response


import com.google.gson.annotations.SerializedName

data class SourceJson(
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: Int? = null
)