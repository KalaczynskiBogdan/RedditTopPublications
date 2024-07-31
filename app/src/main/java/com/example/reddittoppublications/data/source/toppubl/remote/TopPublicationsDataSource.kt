package com.example.reddittoppublications.data.source.toppubl.remote

import com.example.reddittoppublications.domain.models.Children

interface TopPublicationsDataSource {
    suspend fun getListOfTopPublications(
        t: String,
        after: String,
        before: String,
        count: Int,
        limit: Int
    ): List<Children>
}