package com.example.domain.usecase.auth

import com.example.domain.entity.auth.RegisterRequestEntity
import com.example.domain.repository.AuthRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
   private val authRepository: AuthRepository
) {
    suspend fun invoke(data: RegisterRequestEntity, image: MultipartBody.Part) =
        authRepository.register(data, image)
}