package com.example.reddittoppublications.domain.models

data class DataX(
    val author: String,
    val created: Double,
    val createdUtc: Double,
    val numComments: Int,
    val preview: Preview,
    val title: String
)