package com.example.reddittoppublications.data.network.modules

import com.example.reddittoppublications.AUTHORIZATION
import com.example.reddittoppublications.data.network.api.TopPublicationsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://oauth.reddit.com/"
    private const val ACCEPT_HEADER = "application/json"
    private const val AUTHORIZATION_HEADER = AUTHORIZATION


    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val headersInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Accept", ACCEPT_HEADER)
            .addHeader("Authorization", AUTHORIZATION_HEADER)
            .build()
         chain.proceed(request)
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(headersInterceptor)
        .addInterceptor(logger)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideTopPublicationsApi(retrofit: Retrofit): TopPublicationsApi = retrofit.create(TopPublicationsApi::class.java)
}
