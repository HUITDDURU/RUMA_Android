package com.example.domain.entity.auth

data class LoginResponseEntity(
    val accessToken: String,
    val refreshToken: String
)
