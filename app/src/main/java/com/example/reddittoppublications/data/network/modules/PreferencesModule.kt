package com.example.reddittoppublications.data.network.modules

import android.content.Context
import com.example.reddittoppublications.data.helper.PreferencesHelper
import com.example.reddittoppublications.data.repository.PreferencesRepositoryImpl
import com.example.reddittoppublications.domain.repository.PreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreferencesHelper(@ApplicationContext context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun providePreferencesRepository(preferencesHelper: PreferencesHelper): PreferencesRepository {
        return PreferencesRepositoryImpl(preferencesHelper)
    }
}