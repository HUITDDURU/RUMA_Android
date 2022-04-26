package com.example.domain.usecase.auth

import com.example.domain.entity.auth.SendCodeRequest
import com.example.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class SendCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(data: SendCodeRequest) : Response<Unit> =
        authRepository.sendCode(data)
}