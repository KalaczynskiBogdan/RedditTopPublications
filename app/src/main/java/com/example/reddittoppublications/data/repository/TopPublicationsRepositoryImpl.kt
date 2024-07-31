package com.example.reddittoppublications.data.repository

import com.example.reddittoppublications.data.source.toppubl.remote.TopPublicationsDataSource
import com.example.reddittoppublications.domain.models.Children
import com.example.reddittoppublications.domain.repository.TopPublicationsRepository
import javax.inject.Inject

class TopPublicationsRepositoryImpl @Inject constructor(
    private val topPublicationsDataSource: TopPublicationsDataSource
) : TopPublicationsRepository {
    override suspend fun getListOfTopPublications(
        t: String,
        after: String,
        before: String,
        count: Int,
        limit: Int
    ): List<Children> =
        topPublicationsDataSource.getListOfTopPublications(t, after, before, count, limit)
}