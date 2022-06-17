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
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.transports.WebSocket
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://3.39.139.93:9000/"
    private const val SOCKET_BASE_URL = "http://3.39.139.93:9001/"

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
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Singleton
    @Provides
    fun provideSocket(options: IO.Options): Socket =
        IO.socket(SOCKET_BASE_URL, options)

    @Provides
    fun provideOptions(okHttpClient: OkHttpClient): IO.Options =
        IO.Options().apply {
            callFactory = okHttpClient
            webSocketFactory = okHttpClient
            transports = arrayOf(WebSocket.NAME)
        }

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