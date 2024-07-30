package com.example.tesssst.model


import com.google.gson.annotations.SerializedName

data class ChildrenGson(
    @SerializedName("data")
    val `data`: DataGsonX? = DataGsonX(),
    @SerializedName("kind")
    val kind: String? = ""
)