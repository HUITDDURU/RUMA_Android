package com.example.domain.entity.auth

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
