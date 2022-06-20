package com.example.data.repository

import com.example.data.datasource.SocketDataSource
import com.example.domain.entity.user.UserInfoResponseEntity
import com.example.domain.repository.SocketRepository
import javax.inject.Inject

class SocketRepositoryImpl @Inject constructor(
    private val socketDataSource: SocketDataSource
): SocketRepository {
    override suspend fun connect() {
        TODO("Not yet implemented")
    }

    override suspend fun disconnect() {
        TODO("Not yet implemented")
    }

    override suspend fun matching() {
        TODO("Not yet implemented")
    }

    override suspend fun cancel() {
        TODO("Not yet implemented")
    }

    override suspend fun accept(accept: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun localMatching(code: String) {
        TODO("Not yet implemented")
    }

    override suspend fun userInfo(): UserInfoResponseEntity {
        TODO("Not yet implemented")
    }
}