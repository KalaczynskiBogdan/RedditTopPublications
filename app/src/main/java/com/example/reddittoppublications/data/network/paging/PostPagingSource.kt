package com.example.reddittoppublications.data.network.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reddittoppublications.data.source.toppubl.remote.TopPublicationsDataSource
import com.example.reddittoppublications.domain.models.Children
import javax.inject.Inject

class PostPagingSource @Inject constructor(
    private val topPublicationsDataSource: TopPublicationsDataSource
) : PagingSource<String, Children>() {

    override fun getRefreshKey(state: PagingState<String, Children>): String? {
            val anchorPosition = state.anchorPosition ?: return null
            val closestPage = state.closestPageToPosition(anchorPosition) ?: return null
            return closestPage.nextKey ?: closestPage.prevKey
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Children> {
        val after = if (params is LoadParams.Append) params.key else null
        val limit = params.loadSize
        Log.d(
            "PostPagingDataSource",
            "Loading data: after=$after, limit=$limit"
        )
        val response =
            topPublicationsDataSource.getListOfTopPublications(
                "day",
                after,
                null,
                0,
                limit = limit
            )
        val lastItem = response.lastOrNull()
        val nextKey = if (response.isNotEmpty()) lastItem?.dataX?.id?.let { "t3_$it" } else null

        val firstItem = response.firstOrNull()
        val prevKey = if (params is LoadParams.Prepend && response.isNotEmpty()) firstItem?.dataX?.id?.let { "t3_$it" } else null

        Log.d("PostPagingDataSource", "Loaded data: ${response.size}, nextKey: $nextKey, prevKey:")

        return LoadResult.Page(
            data = response,
            prevKey = prevKey,
            nextKey = nextKey
        )
    }
}