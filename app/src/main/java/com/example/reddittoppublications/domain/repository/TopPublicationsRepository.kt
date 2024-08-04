package com.example.reddittoppublications.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.reddittoppublications.domain.models.Children

interface TopPublicationsRepository {
    suspend fun getListOfTopPublications(
        t: String,
        after: String,
        before: String,
        count: Int,
        limit: Int
    ): List<Children>

    fun getPublications(): LiveData<PagingData<Children>>
}