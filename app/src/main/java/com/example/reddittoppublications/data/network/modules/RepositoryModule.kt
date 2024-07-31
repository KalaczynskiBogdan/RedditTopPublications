package com.example.reddittoppublications.data.network.modules

import com.example.reddittoppublications.data.repository.TopPublicationsRepositoryImpl
import com.example.reddittoppublications.domain.repository.TopPublicationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(impl: TopPublicationsRepositoryImpl): TopPublicationsRepository
}