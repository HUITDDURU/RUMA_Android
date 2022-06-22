package com.example.domain.repository

import com.example.domain.entity.user.UserInfoResponseEntity

interface SocketRepository {
    suspend fun connect()
    suspend fun disconnect()
    suspend fun matching()
    suspend fun matchingCancel()
    suspend fun accept(accept: Boolean)
    suspend fun localMatching(code: String)
    suspend fun userInfo(): UserInfoResponseEntity
}