package com.example.domain.usecase.auth

import com.example.domain.repository.AuthRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class FileUploadUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend fun invoke(file: MultipartBody.Part) =
        authRepository.fileUpload(file)
}