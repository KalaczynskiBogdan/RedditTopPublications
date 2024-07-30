package com.example.tesssst.model


import com.google.gson.annotations.SerializedName

data class DataGsonX(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("created")
    val created: Double? = 0.0,
    @SerializedName("created_utc")
    val createdUtc: Double? = 0.0,
    @SerializedName("num_comments")
    val numComments: Int? = 0,
    @SerializedName("preview")
    val preview: PreviewGson? = PreviewGson(),
    @SerializedName("title")
    val title: String? = "",
)