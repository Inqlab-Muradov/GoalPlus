package com.example.goalplus.di

import com.example.goalplus.common.BASE_URL
import com.example.goalplus.data.api.ApiKeyInterceptor
import com.example.goalplus.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiKeyInterceptor():ApiKeyInterceptor{
        return ApiKeyInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkhttp(apiKeyInterceptor: ApiKeyInterceptor):OkHttpClient{
        return OkHttpClient().newBuilder().addNetworkInterceptor(apiKeyInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client:OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }
}