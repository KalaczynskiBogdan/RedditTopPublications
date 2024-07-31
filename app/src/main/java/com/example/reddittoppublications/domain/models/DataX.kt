package com.example.reddittoppublications.domain.models

data class DataX(
    val id: String,
    val author: String,
    val created: Double,
    val createdUtc: Double,
    val postHint: String,
    val numComments: Int,
    val preview: Preview,
    val thumbnail: String,
    val isVideo: Boolean
)