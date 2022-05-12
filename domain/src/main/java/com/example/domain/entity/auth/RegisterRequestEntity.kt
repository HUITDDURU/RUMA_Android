package com.example.domain.entity.auth

data class RegisterRequestEntity(
    val name: String,
    val email: String,
    val password: String,
    val intro: String?,
    val imageUrl: String?
    )
