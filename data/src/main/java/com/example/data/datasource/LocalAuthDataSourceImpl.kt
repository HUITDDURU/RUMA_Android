package com.example.data.datasource

import com.example.data.local.storage.LocalDataStorage
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val localDataStorage: LocalDataStorage
) : LocalAuthDataSource {
    override suspend fun setAccessToken(accessToken: String) {
        localDataStorage.setAccessToken(accessToken)
    }

    override suspend fun getAccessToken(): String? =
        localDataStorage.getAccessToken()

    override suspend fun resetAccessToken() {
        localDataStorage.setAccessToken("")
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        localDataStorage.setRefreshToken(refreshToken)
    }

    override suspend fun getRefreshToken(): String? =
        localDataStorage.getRefreshToken()

    override suspend fun resetRefreshToken() {
        localDataStorage.setRefreshToken("")
    }
}