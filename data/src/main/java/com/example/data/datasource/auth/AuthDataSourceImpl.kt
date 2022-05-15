package com.example.data.datasource.auth

import com.example.data.remote.api.AuthAPI
import com.example.data.remote.request.CertifyRequest
import com.example.data.remote.request.LoginRequest
import com.example.data.remote.request.RegisterRequest
import com.example.data.remote.request.SendCodeRequest
import com.example.data.remote.response.LoginResponse
import com.example.data.remote.response.TokenRefreshResponse
import com.example.domain.base.ErrorHandler
import okhttp3.MultipartBody
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val api: AuthAPI,
    private val errorHandler: ErrorHandler
) : AuthDataSource {
    override suspend fun login(
        body: LoginRequest
    ): LoginResponse = api.login(body)


    override suspend fun register(
        body: RegisterRequest
    ){
        errorHandler { api.register(body) }
    }

    override suspend fun sendCode(
        body: SendCodeRequest
    ){
        errorHandler { api.sendCode(body) }
    }

    override suspend fun certify(
        body: CertifyRequest
    ){
        errorHandler { api.certify(body) }
    }

    override suspend fun tokenRefresh(
        header: String
    ): TokenRefreshResponse = errorHandler { api.tokenRefresh(header) }

    override suspend fun fileUpload(
        file: MultipartBody.Part
    ): HashMap<String, String> = errorHandler { api.fileUpload(file) }

}