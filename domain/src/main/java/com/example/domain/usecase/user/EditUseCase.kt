package com.example.domain.usecase.user

import com.example.domain.entity.user.EditRequestEntity
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class EditUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend fun invoke(header: String, body: EditRequestEntity) =
        userRepository.edit(header, body)
}