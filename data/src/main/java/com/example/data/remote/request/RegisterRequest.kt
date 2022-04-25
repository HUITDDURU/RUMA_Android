package com.example.data.remote.request

import okhttp3.MultipartBody

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val intro: String,
    val file: MultipartBody.Part?
)