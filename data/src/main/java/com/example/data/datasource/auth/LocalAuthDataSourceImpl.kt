package com.example.data.datasource.auth

import com.example.data.local.storage.LocalDataStorage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val localDataStorage: LocalDataStorage
) : LocalAuthDataSource {
    override suspend fun setAccessToken(accessToken: String) {
        localDataStorage.setAccessToken(accessToken)
    }

    override suspend fun getAccessToken(): Flow<String> =
        localDataStorage.getAccessToken()

    override suspend fun resetAccessToken() {
        localDataStorage.setAccessToken("")
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        localDataStorage.setRefreshToken(refreshToken)
    }

    override suspend fun getRefreshToken(): Flow<String> =
        localDataStorage.getRefreshToken()

    override suspend fun resetRefreshToken() {
        localDataStorage.setRefreshToken("")
    }
}