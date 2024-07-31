package com.example.reddittoppublications.data.network.modules

import com.example.reddittoppublications.data.source.toppubl.remote.TopPublicationsDataSource
import com.example.reddittoppublications.data.source.toppubl.remote.TopPublicationsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindDataSource(impl: TopPublicationsDataSourceImpl): TopPublicationsDataSource
}