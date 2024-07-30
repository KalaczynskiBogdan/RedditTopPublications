package com.example.reddittoppublications.data.source.toppubl.remote.dto.response


import com.google.gson.annotations.SerializedName

data class ChildrenJson(
    @SerializedName("data")
    val dataX: DataXJson? = DataXJson(),
    @SerializedName("kind")
    val kind: String? = ""
)