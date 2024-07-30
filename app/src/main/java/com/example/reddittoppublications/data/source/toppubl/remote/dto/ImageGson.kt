package com.example.tesssst.model


import com.google.gson.annotations.SerializedName

data class ImageGson(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("source")
    val source: SourceGson? = SourceGson(),

)