package com.example.reddittoppublications.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.reddittoppublications.data.network.paging.PostPagingSource
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

    override fun getPublications(): LiveData<PagingData<Children>> = Pager(
        config = PagingConfig(pageSize = 10, enablePlaceholders = false, prefetchDistance = 1, initialLoadSize = 10),
        pagingSourceFactory = { PostPagingSource(topPublicationsDataSource) }
    ).liveData

}