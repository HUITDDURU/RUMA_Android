package com.example.data.datasource

interface LocalAuthDataSource {

    suspend fun setAccessToken(accessToken: String)
    suspend fun getAccessToken(): String?
    suspend fun resetAccessToken()

    suspend fun setRefreshToken(refreshToken: String)
    suspend fun getRefreshToken(): String?
    suspend fun resetRefreshToken()

}