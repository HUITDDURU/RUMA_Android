package com.example.domain.socket

import com.example.domain.entity.match.ErrorResponseEntity
import com.example.domain.entity.user.UserInfoResponseEntity
import kotlinx.coroutines.flow.Flow

interface SocketInterface {
    suspend fun connect()
    suspend fun matching()
    suspend fun cancel()
    suspend fun localMatching(code: String)
    suspend fun userInfo(): Flow<UserInfoResponseEntity>
    suspend fun unexpected(): Flow<HashMap<String, String>>
    suspend fun success(): Flow<HashMap<String, String>>
    suspend fun error(): Flow<ErrorResponseEntity>
}