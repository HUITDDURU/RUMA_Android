package com.example.huitdduru.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.storage.LocalDataStorage
import com.example.domain.base.*
import com.example.domain.entity.user.EditRequestEntity
import com.example.domain.usecase.auth.FileUploadUseCase
import com.example.domain.usecase.user.DiaryListUseCase
import com.example.domain.usecase.user.EditUseCase
import com.example.domain.usecase.user.ResignUseCase
import com.example.domain.usecase.user.UserInfoUseCase
import com.example.huitdduru.util.MutableEventFlow
import com.example.huitdduru.util.asEventFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val editUseCase: EditUseCase,
    private val resignUseCase: ResignUseCase,
    private val diaryListUseCase: DiaryListUseCase,
    private val userInfoUseCase: UserInfoUseCase,
    private val fileUploadUseCase: FileUploadUseCase,
    private val localDataStorage: LocalDataStorage
): ViewModel() {
    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    var imageUrl: String? = ""

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    fun edit(intro: String?){
        viewModelScope.launch {
            kotlin.runCatching {
                editUseCase.invoke(
                    localDataStorage.getAccessToken()!!,
                    EditRequestEntity(intro, imageUrl)
                )
            }.onSuccess {
                event(Event.SuccessEdit(true))
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    fun resign(){
        viewModelScope.launch {
            kotlin.runCatching {
                resignUseCase.invoke(
                    localDataStorage.getAccessToken()!!
                )
            }.onSuccess {
                event(Event.SuccessResign(true))
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    fun diaryList(){
        viewModelScope.launch {
            kotlin.runCatching {
                diaryListUseCase.invoke(
                    localDataStorage.getAccessToken()!!
                )
            }.onSuccess {
                event(Event.SuccessGetDiaryList(true))
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    fun userInfo(){
        viewModelScope.launch {
            kotlin.runCatching {
                userInfoUseCase.invoke(
                    localDataStorage.getAccessToken()!!
                )
            }.onSuccess {
                event(Event.SuccessUserInfo(true))
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    fun imageUpload(file: MultipartBody.Part){
        viewModelScope.launch {
            kotlin.runCatching {
                fileUploadUseCase.invoke(file)
            }.onSuccess {
                imageUrl = it["imageUrl"]
                event(Event.SuccessFileUpload(true))
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
        data class SuccessEdit(var state: Boolean = false) : Event()
        data class SuccessResign(var state: Boolean = false) : Event()
        data class SuccessGetDiaryList(var state: Boolean = false) : Event()
        data class SuccessUserInfo(var state: Boolean = false) : Event()
        data class SuccessFileUpload(var state: Boolean = false) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()

    }
}