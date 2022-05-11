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
import okhttp3.MultipartBody
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val api: AuthAPI
) : AuthDataSource, SafeApiCall() {
    override suspend fun login(
        body: LoginRequest
    ): LoginResponse = api.login(body)


    override suspend fun register(
        part: RegisterRequest,
        image: MultipartBody.Part?
    ): Unit = api.register(part, image)

    override suspend fun sendCode(
        body: SendCodeRequest
    ): Unit = api.sendCode(body)

    override suspend fun certify(
        body: CertifyRequest
    ): Unit = api.certify(body)

    override suspend fun tokenRefresh(
        header: String
    ): TokenRefreshResponse = api.tokenRefresh(header)

}