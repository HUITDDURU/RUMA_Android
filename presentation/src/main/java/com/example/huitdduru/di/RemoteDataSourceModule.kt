package com.example.huitdduru.di

import com.example.data.datasource.AuthDataSource
import com.example.data.datasource.AuthDataSourceImpl
import com.example.data.datasource.DiaryDataSource
import com.example.data.datasource.DiaryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Singleton
    @Binds
    abstract fun provideAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Singleton
    @Binds
    abstract fun provideDiaryDataSource(
        diaryDataSourceImpl: DiaryDataSourceImpl
    ): DiaryDataSource
}