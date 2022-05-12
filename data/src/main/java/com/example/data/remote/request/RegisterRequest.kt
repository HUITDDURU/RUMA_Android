package com.example.data.remote.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val intro: String?,
    val imageUrl: String?
)