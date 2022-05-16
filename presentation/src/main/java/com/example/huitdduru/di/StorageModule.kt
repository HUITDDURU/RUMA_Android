package com.example.huitdduru.di

import android.content.Context
import com.example.data.local.storage.LocalDataStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Singleton
    @Provides
    fun provideLocalStorage(@ApplicationContext context: Context): LocalDataStorage =
        LocalDataStorage(context)
}