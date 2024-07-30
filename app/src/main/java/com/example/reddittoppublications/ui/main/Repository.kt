package com.example.reddittoppublications.ui.main

import com.example.reddittoppublications.data.network.api.TopPublicationsApi
import javax.inject.Inject

class Repository@Inject constructor(
    private val topPublicationsApi: TopPublicationsApi
) {

    suspend fun getList() = topPublicationsApi.getTopPublicationsList(
        t = "day",
        after = "t1_c3v7f8",
        before = "t1_c3v7f9",
        count = 3,
        limit = 5
    )

}