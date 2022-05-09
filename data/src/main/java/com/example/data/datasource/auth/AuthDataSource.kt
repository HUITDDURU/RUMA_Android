package com.example.data.datasource.auth

import com.example.data.remote.request.CertifyRequest
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.RegisterRequest
import com.example.data.remote.request.SendCodeRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.TokenRefreshResponse
import com.example.domain.util.RemoteErrorEmitter

interface AuthDataSource {
    suspend fun login(remoteErrorEmitter: RemoteErrorEmitter ,body: LoginRequest) : LoginResponse
    suspend fun register(remoteErrorEmitter: RemoteErrorEmitter ,part: RegisterRequest)
    suspend fun sendCode(remoteErrorEmitter: RemoteErrorEmitter ,body: SendCodeRequest)
    suspend fun certify(remoteErrorEmitter: RemoteErrorEmitter ,body: CertifyRequest)
    suspend fun tokenRefresh(remoteErrorEmitter: RemoteErrorEmitter ,header: String) : TokenRefreshResponse
}