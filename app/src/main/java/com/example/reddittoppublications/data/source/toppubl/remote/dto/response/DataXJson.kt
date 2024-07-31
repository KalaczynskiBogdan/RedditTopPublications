package com.example.reddittoppublications.data.source.toppubl.remote.dto.response


import com.google.gson.annotations.SerializedName

data class DataXJson(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("created")
    val created: Double? = 0.0,
    @SerializedName("created_utc")
    val createdUtc: Double? = 0.0,
    @SerializedName("post_hint")
    val postHint: String? = "",
    @SerializedName("num_comments")
    val numComments: Int? = 0,
    @SerializedName("preview")
    val preview: PreviewJson? = PreviewJson(),
    @SerializedName("thumbnail")
    val thumbnail: String? = "",
    @SerializedName("is_video")
    val isVideo: Boolean? = false,
)