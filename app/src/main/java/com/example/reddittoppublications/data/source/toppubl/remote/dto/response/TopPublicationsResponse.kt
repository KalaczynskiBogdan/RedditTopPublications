package com.example.reddittoppublications.data.source.toppubl.remote.dto.response


import com.google.gson.annotations.SerializedName

data class TopPublicationsResponse(
    @SerializedName("data")
    val `data`: DataJson? = DataJson(),
    @SerializedName("kind")
    val kind: String? = ""
)