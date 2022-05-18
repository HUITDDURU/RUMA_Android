package com.example.huitdduru.viewmodel.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.storage.LocalDataStorage
import com.example.domain.entity.diary.WriteDiaryRequestEntity
import com.example.domain.usecase.diary.DiaryTimeLineUseCase
import com.example.domain.usecase.diary.GetDiaryListUseCase
import com.example.domain.usecase.diary.WriteDiaryUseCase
import com.example.huitdduru.util.MutableEventFlow
import com.example.huitdduru.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val writeDiaryUseCase: WriteDiaryUseCase,
    private val getDiaryListUseCase: GetDiaryListUseCase,
    private val diaryTimeLineUseCase: DiaryTimeLineUseCase,
    private val localDataStorage: LocalDataStorage
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun writeDiary(
        diaryId: Int,
        title: String,
        feeling: String,
        date: LocalDateTime,
        content: String,
        imageUrl: String?
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                writeDiaryUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    diaryId,
                    WriteDiaryRequestEntity(
                        title,
                        feeling,
                        date,
                        content,
                        imageUrl
                    )
                )
            }.onSuccess {
                event(Event.SuccessWrite(true))
            }.onFailure {

            }
        }
    }

    sealed class Event {
        data class SuccessWrite(val status: Boolean = false) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()
    }
}