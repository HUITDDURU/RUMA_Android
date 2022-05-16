package com.example.huitdduru.di

import com.example.data.base.ErrorHandlerImpl
import com.example.domain.base.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlerModule {

    @Binds
    abstract fun provideErrorHandler(
        errorHandlerImpl: ErrorHandlerImpl
    ): ErrorHandler
}