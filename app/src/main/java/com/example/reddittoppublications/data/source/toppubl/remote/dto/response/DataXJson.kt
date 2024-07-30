package com.example.reddittoppublications.data.source.toppubl.remote.dto.response


import com.google.gson.annotations.SerializedName

data class DataXJson(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("created")
    val created: Double? = 0.0,
    @SerializedName("created_utc")
    val createdUtc: Double? = 0.0,
    @SerializedName("num_comments")
    val numComments: Int? = 0,
    @SerializedName("preview")
    val preview: PreviewJson? = PreviewJson(),
    @SerializedName("title")
    val title: String? = "",
)