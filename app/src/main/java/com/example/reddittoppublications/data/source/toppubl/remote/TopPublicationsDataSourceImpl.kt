package com.example.reddittoppublications.data.source.toppubl.remote

import com.example.reddittoppublications.data.network.api.TopPublicationsApi
import com.example.reddittoppublications.data.source.toppubl.toUi
import com.example.reddittoppublications.domain.models.Children
import javax.inject.Inject

class TopPublicationsDataSourceImpl @Inject constructor(
    private val topPublicationsApi: TopPublicationsApi
): TopPublicationsDataSource{
    override suspend fun getListOfTopPublications(
        t: String,
        after: String,
        before: String,
        count: Int,
        limit: Int
    ): List<Children> {
        return topPublicationsApi.getTopPublicationsList(t,after, before, count, limit).toUi().data.children
    }
}