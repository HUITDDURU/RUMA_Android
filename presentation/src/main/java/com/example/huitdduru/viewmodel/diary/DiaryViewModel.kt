package com.example.huitdduru.viewmodel.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.storage.LocalDataStorage
import com.example.domain.base.*
import com.example.domain.entity.diary.*
import com.example.domain.usecase.diary.*
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
    private val diaryTimeLineUseCase: GetMonthDiaryUseCase,
    private val getDateDiaryUseCase: GetDateDiaryUseCase,
    private val diaryDetailUseCase: DiaryDetailUseCase,
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
                    localDataStorage.getDiaryId(),
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
                errorMessage(throwable = it)
            }
        }
    }

    fun getDiaryList(diaryId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                getDiaryListUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    diaryId
                )
            }.onSuccess {
                Event.SuccessGetList(it)
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    fun getMonthDiary(year: Int, month: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                diaryTimeLineUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    year,
                    month
                )
            }.onSuccess {
                event(Event.SuccessTimeLine(it))
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    fun getDateDiary(date: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                getDateDiaryUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    date
                )
            }.onSuccess {
                event(Event.SuccessGetDateDiary(it))
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    fun diaryDetail(diaryId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                diaryDetailUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    diaryId
                )
            }.onSuccess {
                event(Event.SuccessDiaryDetail(it))
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    private fun errorMessage(throwable: Throwable) {
        when (throwable) {
            is BadRequest -> event(Event.ErrorMessage("잘못된 요청 형식입니다."))
            is UnAuthorized -> event(Event.ErrorMessage("만료된 토큰입니다."))
            is Forbidden -> event(Event.ErrorMessage("요청 권한이 없습니다."))
            is NotFound -> event(Event.ErrorMessage("잘못된 요청입니다."))
            is ServerError -> event(Event.ErrorMessage("알 수 없는 오류가 발생했습니다."))
        }
    }

    sealed class Event {
        data class SuccessWrite(val status: Boolean = false) : Event()
        data class SuccessGetList(val diaryList: GetDiaryListResponseEntity) : Event()
        data class SuccessTimeLine(val monthDiary: List<MonthDiaryResponseEntity>) : Event()
        data class SuccessGetDateDiary(val dateDiary: List<DateDiaryResponseEntity>) : Event()
        data class SuccessDiaryDetail(val diaryDetail: DiaryDetailResponseEntity) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()
    }
}


