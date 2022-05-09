package com.example.domain.usecase.auth

import com.example.domain.entity.auth.RegisterRequestEntity
import com.example.domain.repository.AuthRepository
import com.example.domain.util.RemoteErrorEmitter
import retrofit2.Response
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
   private val authRepository: AuthRepository
) {
    suspend fun invoke(remoteErrorEmitter: RemoteErrorEmitter, data: RegisterRequestEntity) =
        authRepository.register(remoteErrorEmitter, data)
}