package com.example.huitdduru.di

import com.example.data.datasource.*
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

    @Singleton
    @Binds
    abstract fun provideUserDataSource(
        userDataSourceImpl: UserDataSourceImpl
    ): UserDataSource
}