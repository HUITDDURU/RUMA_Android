package com.example.huitdduru.di

import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.DiaryRepositoryImpl
import com.example.data.repository.SocketRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.DiaryRepository
import com.example.domain.repository.SocketRepository
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ) : AuthRepository

    @Singleton
    @Binds
    abstract fun provideDiaryRepository(
        diaryRepositoryImpl: DiaryRepositoryImpl
    ) : DiaryRepository

    @Singleton
    @Binds
    abstract fun provideUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ) : UserRepository

    @Singleton
    @Binds
    abstract fun provideSocketRepository(
        socketRepositoryImpl: SocketRepositoryImpl
    ) : SocketRepository
}