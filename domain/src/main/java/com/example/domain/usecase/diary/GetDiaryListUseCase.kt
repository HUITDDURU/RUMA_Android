package com.example.domain.usecase.diary

import com.example.domain.entity.diary.GetDiaryListResponseEntity
import com.example.domain.repository.DiaryRepository
import javax.inject.Inject

class GetDiaryListUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository
) {
    suspend fun invoke(
        header: String,
        diaryId: Int
    ): GetDiaryListResponseEntity =
        diaryRepository.getDiaryList(header, diaryId)
}