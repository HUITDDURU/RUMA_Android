package com.example.data.datasource

import com.example.domain.entity.match.ErrorResponseEntity
import com.example.domain.entity.user.UserInfoResponseEntity
import io.socket.client.Socket
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class SocketDataSourceImpl @Inject constructor(
    private val socket: Socket
): SocketDataSource{
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

    override suspend fun accept() {
        TODO("Not yet implemented")
    }

    override suspend fun localMatching(code: String) {
        TODO("Not yet implemented")
    }

    override suspend fun userInfo(): UserInfoResponseEntity {
        TODO("Not yet implemented")
    }

    override suspend fun unexpectedCancel(): SharedFlow<HashMap<String, String>> {
        TODO("Not yet implemented")
    }

    override suspend fun success(): SharedFlow<HashMap<String, String>> {
        TODO("Not yet implemented")
    }

    override suspend fun error(): ErrorResponseEntity {
        TODO("Not yet implemented")
    }

}