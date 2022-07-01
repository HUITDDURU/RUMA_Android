package com.example.data.remote.api

import com.example.data.remote.response.MateResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface MatchAPI {
    @GET("/match")
    suspend fun mate(
        @Header("Authorization") header: String
    ): MateResponse

    @PUT("/match")
    suspend fun terminate(
        @Header("Authorization") header: String
    )
}