package com.example.data.datasource.auth

import com.example.data.remote.api.AuthAPI
import com.example.data.remote.request.CertifyRequest
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.RegisterRequest
import com.example.data.remote.request.SendCodeRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.TokenRefreshResponse
import com.example.data.util.SafeApiCall
import com.example.domain.util.RemoteErrorEmitter
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val api: AuthAPI
) : AuthRemoteDataSource, SafeApiCall() {
    override suspend fun login(
        remoteErrorEmitter: RemoteErrorEmitter,
        body: LoginRequest
    ): LoginResponse {
        return safeApiCall(remoteErrorEmitter) {
            api.login(body).body()
        }!!
    }

    override suspend fun register(
        remoteErrorEmitter: RemoteErrorEmitter,
        part: RegisterRequest
    ) {
        return safeApiCall(remoteErrorEmitter) {
            api.register(part).body()
        }!!
    }

    override suspend fun sendCode(
        remoteErrorEmitter: RemoteErrorEmitter,
        body: SendCodeRequest
    ) {
        return safeApiCall(remoteErrorEmitter) {
            api.sendCode(body).body()
        }!!
    }

    override suspend fun certify(
        remoteErrorEmitter: RemoteErrorEmitter,
        body: CertifyRequest
    ) {
        return safeApiCall(remoteErrorEmitter) {
            api.certify(body).body()
        }!!
    }

    override suspend fun tokenRefresh(
        remoteErrorEmitter: RemoteErrorEmitter,
        header: String
    ): TokenRefreshResponse {
        return safeApiCall(remoteErrorEmitter) {
            api.tokenRefresh(header).body()
        }!!
    }
}