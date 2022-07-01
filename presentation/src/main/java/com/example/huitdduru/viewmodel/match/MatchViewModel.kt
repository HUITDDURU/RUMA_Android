package com.example.huitdduru.viewmodel.match

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.storage.LocalDataStorage
import com.example.domain.base.*
import com.example.domain.entity.match.MateResponseEntity
import com.example.domain.entity.user.UserInfoResponseEntity
import com.example.domain.usecase.match.*
import com.example.huitdduru.util.MutableEventFlow
import com.example.huitdduru.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val connectSocketUseCase: ConnectSocketUseCase,
    private val disconnectSocketUseCase: DisconnectSocketUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val localMatchingUseCase: LocalMatchingUseCase,
    private val matchingAcceptUseCase: MatchingAcceptUseCase,
    private val matchingCancelUseCase: MatchingCancelUseCase,
    private val matchingUseCase: MatchingUseCase,
    private val cancelUseCase: CancelUseCase,
    private val successUseCase: SuccessUseCase,
    private val mateUseCase: MateUseCase,
    private val terminateUseCase: TerminateUseCase,
    private val localDataStorage: LocalDataStorage
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    private val _sharedFlow = MutableSharedFlow<String>(replay = 0)
    val sharedFlow = _sharedFlow.asSharedFlow()

    private var userInfo : UserInfoResponseEntity? = null

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    private fun receiveMessage(message: String) {
        viewModelScope.launch {
            _sharedFlow.emit(message)
        }
    }

    fun connect(){
        viewModelScope.launch {
            connectSocketUseCase.invoke()
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            disconnectSocketUseCase.invoke()
        }
    }

    fun matching() {
        viewModelScope.launch {
            matchingUseCase.invoke()
        }
    }

    fun localMatching(code: String) {
        viewModelScope.launch {
            localMatchingUseCase.invoke(code)
        }
    }

    fun matchingAccept(accept: Boolean) {
        viewModelScope.launch {
            matchingAcceptUseCase.invoke(accept)
        }
    }

    fun matchingCancel() {
        viewModelScope.launch {
            matchingCancelUseCase.invoke()
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase.invoke().runCatching {
                collect{
                    event(Event.SuccessFindUser(true))
                    userInfo = it
                }
            }
        }
    }

    fun cancel() {
        viewModelScope.launch {
            kotlin.runCatching {
                cancelUseCase.invoke()
            }.onSuccess { flow ->
                flow.collect { message ->
                    receiveMessage(message)
                    event(Event.SuccessMatch(false))
                    event(Event.SuccessCancel(true))
                }
            }.onFailure {
                event(Event.ErrorMessage("오류가 발생했습니다."))
            }
        }
    }

    fun success() {
        viewModelScope.launch {
            kotlin.runCatching {
                successUseCase.invoke()
            }.onSuccess { flow ->
                flow.collect { message ->
                    receiveMessage(message)
                    event(Event.SuccessCancel(false))
                    event(Event.SuccessMatch(true))
                }
            }.onFailure {
                event(Event.ErrorMessage("오류가 발생했습니다."))
            }
        }
    }

    fun mate(){
        viewModelScope.launch {
            kotlin.runCatching {
                mateUseCase.invoke(localDataStorage.getAccessToken()!!)
            }.onSuccess {
                event(Event.SuccessMate(mate = it))
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    fun terminate(){
        viewModelScope.launch {
            kotlin.runCatching {
                terminateUseCase.invoke(localDataStorage.getAccessToken()!!)
            }.onSuccess {
                event(Event.SuccessTerminate(true))
            }.onFailure {
                errorMessage(throwable = it)
            }
        }
    }

    fun getUser() = userInfo

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
        data class SuccessFindUser(var status: Boolean = false) : Event()
        data class SuccessMatch(var status: Boolean = false) : Event()
        data class SuccessCancel(var status: Boolean = false) : Event()
        data class SuccessMate(val mate: MateResponseEntity) : Event()
        data class SuccessTerminate(var status: Boolean = false) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()
    }
}