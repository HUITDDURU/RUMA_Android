package com.example.domain.usecase.auth

import com.example.domain.entity.auth.LoginRequest
import com.example.domain.entity.auth.LoginResponse
import com.example.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(data: LoginRequest) : Response<LoginResponse> =
        authRepository.login(data)
}