package com.example.huitdduru.di

import com.example.data.remote.api.AuthAPI
import com.example.data.remote.api.DiaryAPI
import com.example.data.remote.api.UserAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().apply {
            readTimeout(10, TimeUnit.SECONDS)
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) : Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://3.39.139.93:9000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideAuthAPI(retrofit: Retrofit) : AuthAPI =
        retrofit.create(AuthAPI::class.java)

    @Provides
    fun provideDiaryAPI(retrofit: Retrofit) : DiaryAPI =
        retrofit.create(DiaryAPI::class.java)

    @Provides
    fun provideUserAPI(retrofit: Retrofit) : UserAPI =
        retrofit.create(UserAPI::class.java)

    @Provides
    fun provideGsonBuilder() : Gson =
        GsonBuilder()
            .setLenient()
            .create()
}