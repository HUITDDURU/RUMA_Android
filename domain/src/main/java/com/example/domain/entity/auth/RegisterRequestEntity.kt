package com.example.domain.entity.auth

import okhttp3.MultipartBody

data class RegisterRequestEntity(
    val name: String,
    val email: String,
    val password: String,
    val intro: String,
    val file: MultipartBody.Part?
)
