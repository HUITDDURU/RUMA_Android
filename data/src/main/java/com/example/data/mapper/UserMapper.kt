package com.example.data.mapper

import com.example.data.remote.request.EditRequest
import com.example.data.remote.response.DiaryResponse
import com.example.data.remote.response.UserInfoResponse
import com.example.domain.entity.user.DiaryResponseEntity
import com.example.domain.entity.user.EditRequestEntity
import com.example.domain.entity.user.UserInfoResponseEntity

object UserMapper {
    fun mapperToEditRequest(editRequestEntity: EditRequestEntity) =
        editRequestEntity.run {
            EditRequest(intro, imageUrl)
        }

    fun mapperToDiaryEntity(diaryResponse: List<DiaryResponse>) =
        diaryResponse.map {
            DiaryResponseEntity(
                it.diaryId,
                it.currentUserImg,
                it.mateImg,
                it.isMyTurn,
                it.mateName,
                it.hoursAgo
            )
        }

    fun mapperToInfoEntity(userInfoResponse: UserInfoResponse) =
        userInfoResponse.run {
            UserInfoResponseEntity(name, intro, img)
        }
}