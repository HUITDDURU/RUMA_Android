package com.example.data.datasource

import com.example.domain.entity.match.ErrorResponseEntity
import com.example.domain.entity.user.UserInfoResponseEntity
import kotlinx.coroutines.flow.SharedFlow

interface SocketDataSource {
    suspend fun connect()
    suspend fun disconnect()
    suspend fun matching()
    suspend fun cancel()
    suspend fun accept(accept: Boolean)
    suspend fun localMatching(code: String)
    suspend fun userInfo(): UserInfoResponseEntity
    suspend fun unexpectedCancel(): SharedFlow<String>
    suspend fun success(): SharedFlow<String>
    suspend fun error(): ErrorResponseEntity
}