package com.example.reddittoppublications.domain.models

data class Data(
    val after: String,
    val before: Any,
    val children: List<Children>,
    val dist: Int,
    val geoFilter: String,
    val modhash: Any,
)