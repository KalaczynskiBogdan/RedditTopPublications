package com.example.reddittoppublications.data.di

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
    private const val AUTHORIZATION_HEADER =
        "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IlNIQTI1NjpzS3dsMnlsV0VtMjVmcXhwTU40cWY4MXE2OWFFdWFyMnpLMUdhVGxjdWNZIiwidHlwIjoiSldUIn0.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNzIyNDQzMjY1LjE1MTQ2NywiaWF0IjoxNzIyMzU2ODY1LjE1MTQ2NiwianRpIjoiOEFza3RQMnhmbnhVTmYzY3F1Nk5PR1c4WTFBSzNRIiwiY2lkIjoiY2FXQmxkVGhocmFJakFPa1o3RlUydyIsImxpZCI6InQyXzE1bW96Y3JheXkiLCJhaWQiOiJ0Ml8xNW1vemNyYXl5IiwibGNhIjoxNzIyMzU0MzQ1Mzc3LCJzY3AiOiJlSnlLVnRKU2lnVUVBQURfX3dOekFTYyIsImZsbyI6OX0.Eg08SezH2OnjKe380ksrREQAcYdpubk6EfuShDDlx9w8tQzRXYvSyISaVTQdlRNNhsVR-fWQQpWuNBvcsgWpvIn9K0b9rj50Yxm8LnDkHQnbmsc1oYY6_Z3hAOtRXtbR7Gch6Pq1xxyFPuwBtKzVZ8FE8Gqh2GYYSsLEsPGZ12IH6Hfi5F_Y4UcaxqFOqNFr_GJ0AraNldZdfB34sCXn_QVEB4fiULkWMxl1jL2VVrZl69UK6zQKg6RbdtytSYNXTI5VkWumdFBOL-t1Vx2_2bfuF_vadnIz-RT8xQaZ4NXy1fJJX_dFvgApFUeVBIzzR3cHiywLELD8ExIyjs-mtQ"

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
