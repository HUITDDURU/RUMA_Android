package com.example.data.repository

import com.example.data.datasource.SocketDataSource
import com.example.domain.entity.user.UserInfoResponseEntity
import com.example.domain.repository.SocketRepository
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor(
    private val socketDataSource: SocketDataSource
): SocketRepository {
    override suspend fun connect() {
        socketDataSource.connect()
    }

    override suspend fun disconnect() {
        socketDataSource.disconnect()
    }

    override suspend fun matching() {
        socketDataSource.matching()
    }

    override suspend fun cancel() {
        socketDataSource.cancel()
    }

    override suspend fun accept(accept: Boolean) {
        socketDataSource.accept(accept)
    }

    override suspend fun localMatching(code: String) {
        socketDataSource.localMatching(code)
    }

    override suspend fun userInfo(): UserInfoResponseEntity =
        socketDataSource.userInfo()
}