package com.example.reddittoppublications.data.source.toppubl.remote.dto.response


import com.google.gson.annotations.SerializedName

data class ImageJson(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("source")
    val source: SourceJson? = SourceJson(),

    )