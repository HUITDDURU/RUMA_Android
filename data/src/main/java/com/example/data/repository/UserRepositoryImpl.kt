package com.example.data.repository

import com.example.data.datasource.UserDataSource
import com.example.data.mapper.UserMapper.mapperToDiaryEntity
import com.example.data.mapper.UserMapper.mapperToEditRequest
import com.example.data.mapper.UserMapper.mapperToInfoEntity
import com.example.domain.entity.user.DiaryResponseEntity
import com.example.domain.entity.user.EditRequestEntity
import com.example.domain.entity.user.UserInfoResponseEntity
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {
    override suspend fun edit(header: String, body: EditRequestEntity) =
        userDataSource.edit(header, mapperToEditRequest(body))

    override suspend fun resign(header: String) =
        userDataSource.resign(header)

    override suspend fun diaryList(header: String): List<DiaryResponseEntity> =
        mapperToDiaryEntity(userDataSource.diaryList(header))

    override suspend fun userInfo(header: String): UserInfoResponseEntity =
        mapperToInfoEntity(userDataSource.userInfo(header))
}