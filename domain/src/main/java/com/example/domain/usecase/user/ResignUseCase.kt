package com.example.domain.usecase.user

import com.example.domain.repository.UserRepository
import javax.inject.Inject

class ResignUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend fun invoke(header: String) =
        userRepository.resign(header)
}