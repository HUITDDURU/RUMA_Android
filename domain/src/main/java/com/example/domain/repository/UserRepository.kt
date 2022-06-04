package com.example.domain.repository

import com.example.domain.entity.user.DiaryResponseEntity
import com.example.domain.entity.user.EditRequestEntity
import com.example.domain.entity.user.UserInfoResponseEntity

interface UserRepository {
    suspend fun edit(header: String, body: EditRequestEntity)
    suspend fun resign(header: String)
    suspend fun diaryList(header: String): List<DiaryResponseEntity>
    suspend fun userInfo(header: String): UserInfoResponseEntity
}