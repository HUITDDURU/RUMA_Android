package com.example.data.datasource

import com.example.domain.entity.user.UserInfoResponseEntity
import kotlinx.coroutines.flow.SharedFlow

interface SocketDataSource {
    suspend fun connect()
    suspend fun disconnect()
    suspend fun matching()
    suspend fun matchingCancel()
    suspend fun accept(accept: Boolean)
    suspend fun localMatching(code: String)
    suspend fun userInfo(): SharedFlow<UserInfoResponseEntity>
    suspend fun cancel(): SharedFlow<String>
    suspend fun success(): SharedFlow<String>
}