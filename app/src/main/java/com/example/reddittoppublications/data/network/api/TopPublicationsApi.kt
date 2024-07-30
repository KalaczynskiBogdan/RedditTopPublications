package com.example.reddittoppublications.data.network.api

import com.example.tesssst.model.TopResponseJsonGson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopPublicationsApi {

    @GET("r/all/top")
    suspend fun getTopPublicationsList(
        @Query("t") t: String,
        @Query("after") after: String,
        @Query("before") before: String,
        @Query("count") count: Int,
        @Query("limit") limit: Int,
    ): Response<TopResponseJsonGson>
}