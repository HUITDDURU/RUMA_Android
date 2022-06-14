package com.example.data.datasource

import com.example.data.remote.request.EditRequest
import com.example.data.remote.response.DiaryResponse
import com.example.data.remote.response.UserInfoResponse

interface UserDataSource {
    suspend fun edit(header:String, body: EditRequest)
    suspend fun resign(header: String)
    suspend fun diaryList(header: String): List<DiaryResponse>
    suspend fun userInfo(header: String): UserInfoResponse
    suspend fun code(header: String): HashMap<String, String>
}