package com.example.domain.usecase.auth

import com.example.domain.entity.auth.LoginRequestEntity
import com.example.domain.entity.auth.LoginResponseEntity
import com.example.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(data: LoginRequestEntity) : Response<LoginResponseEntity> =
        authRepository.login(data)
}