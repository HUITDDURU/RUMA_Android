package com.example.data.datasource.auth

import com.example.data.remote.api.AuthAPI
import com.example.data.remote.request.CertifyRequest
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.RegisterRequest
import com.example.data.remote.request.SendCodeRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.TokenRefreshResponse
import com.example.data.util.SafeApiCall
import com.example.domain.entity.auth.*
import com.example.domain.util.RemoteErrorEmitter
import retrofit2.Response
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val api: AuthAPI
) : AuthDataSource, SafeApiCall() {
    override suspend fun login(
        remoteErrorEmitter: RemoteErrorEmitter,
        body: LoginRequest
    ): LoginResponse =
        safeApiCall(remoteErrorEmitter) {
            api.login(body)
        }!!


    override suspend fun register(
        remoteErrorEmitter: RemoteErrorEmitter,
        part: RegisterRequest
    ): Unit =
        safeApiCall(remoteErrorEmitter) {
            api.register(part)
        }!!

    override suspend fun sendCode(
        remoteErrorEmitter: RemoteErrorEmitter,
        body: SendCodeRequest
    ): Unit =
        safeApiCall(remoteErrorEmitter) {
            api.sendCode(body)
        }!!

    override suspend fun certify(
        remoteErrorEmitter: RemoteErrorEmitter,
        body: CertifyRequest
    ): Unit =
        safeApiCall(remoteErrorEmitter) {
            api.certify(body)
        }!!

    override suspend fun tokenRefresh(
        remoteErrorEmitter: RemoteErrorEmitter,
        header: String
    ): TokenRefreshResponse =
        safeApiCall(remoteErrorEmitter) {
            api.tokenRefresh(header)
        }!!

}