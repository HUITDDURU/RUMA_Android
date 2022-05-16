package com.example.huitdduru.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.BadRequest
import com.example.domain.base.Conflict
import com.example.domain.base.NotFound
import com.example.domain.base.ServerError
import com.example.domain.entity.auth.CertifyRequestEntity
import com.example.domain.entity.auth.RegisterRequestEntity
import com.example.domain.entity.auth.SendCodeRequestEntity
import com.example.domain.usecase.auth.CertifyUseCase
import com.example.domain.usecase.auth.FileUploadUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.usecase.auth.SendCodeUseCase
import com.example.huitdduru.util.MutableEventFlow
import com.example.huitdduru.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val sendCodeUseCase: SendCodeUseCase,
    private val certifyUseCase: CertifyUseCase,
    private val fileUploadUseCase: FileUploadUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    var name: String = ""
    var email: String = ""
    var password: String = ""
    var intro: String? = ""
    var file: String? = ""

    fun register() {
        viewModelScope.launch {
            kotlin.runCatching {
                registerUseCase.invoke(
                    RegisterRequestEntity(
                        name,
                        email,
                        password,
                        intro,
                        file
                    )
                )
            }.onSuccess {
                event(Event.SuccessRegister(true))
            }.onFailure {
                when(it){
                    is BadRequest -> { event(Event.ErrorMessage("잘못된 요청 구문입니다.")) }
                    is NotFound -> { event(Event.ErrorMessage("잘못된 요청입니다.")) }
                }
            }
        }
    }

    fun sendCode(email: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                sendCodeUseCase.invoke(SendCodeRequestEntity(email))
            }.onSuccess {
                event(Event.SuccessEmailSend(true))
            }.onFailure {
                when(it){
                    is BadRequest -> { event(Event.ErrorMessage("잘못된 요청 구문입니다.")) }
                    is NotFound -> { event(Event.ErrorMessage("잘못된 요청입니다..")) }
                    is Conflict -> { event(Event.ErrorMessage("이미 사용중인 이메일 입니다."))}
                    is ServerError -> { event(Event.ErrorMessage("잘못된 이메일 형식입니다."))}
                }
            }
        }
    }

    fun certify(code: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                certifyUseCase.invoke(CertifyRequestEntity(email, code))
            }.onSuccess {
                event(Event.SuccessEmailCertify(true))
            }.onFailure {
                when(it){
                    is BadRequest -> { event(Event.ErrorMessage("잘못된 요청 구문입니다.")) }
                    is NotFound -> { event(Event.ErrorMessage("인증번호가 일치하지 않습니다.")) }
                }
            }
        }
    }

    fun fileUpload(file: MultipartBody.Part) {
        viewModelScope.launch {
            kotlin.runCatching {
                fileUploadUseCase.invoke(file)
            }.onSuccess {
                setImage(it)
                event(Event.SuccessFileUpload(true))
            }.onFailure {
                when(it){
                    is BadRequest -> { event(Event.ErrorMessage("잘못된 요청 구문입니다.")) }
                    is NotFound -> { event(Event.ErrorMessage("잘못된 요청입니다.")) }
                }
            }
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    private fun setImage(url: HashMap<String, String>) {
        file = url["imageUrl"]
    }

    sealed class Event {
        data class SuccessRegister(var state: Boolean = false) : Event()
        data class SuccessEmailSend(var state: Boolean = false) : Event()
        data class SuccessEmailCertify(var state: Boolean = false) : Event()
        data class SuccessFileUpload(var state: Boolean = false) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()
    }
}