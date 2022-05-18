package com.example.data.datasource

import kotlinx.coroutines.flow.Flow

interface LocalAuthDataSource {

    suspend fun setAccessToken(accessToken: String)
    suspend fun getAccessToken(): Flow<String>
    suspend fun resetAccessToken()

    suspend fun setRefreshToken(refreshToken: String)
    suspend fun getRefreshToken(): Flow<String>
    suspend fun resetRefreshToken()

}