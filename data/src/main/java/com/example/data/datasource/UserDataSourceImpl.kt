package com.example.data.datasource

import com.example.data.remote.api.UserAPI
import com.example.data.remote.request.EditRequest
import com.example.data.remote.response.DiaryResponse
import com.example.data.remote.response.UserInfoResponse
import com.example.domain.base.ErrorHandler
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val userAPI: UserAPI
): UserDataSource{
    override suspend fun edit(header: String, body: EditRequest) =
        errorHandler { userAPI.edit(header, body) }

    override suspend fun resign(header: String) =
        errorHandler { userAPI.resign(header) }

    override suspend fun diaryList(header: String): List<DiaryResponse> =
        errorHandler { userAPI.diaryList(header) }

    override suspend fun userInfo(header: String): UserInfoResponse =
        errorHandler { userAPI.userInfo(header) }
}