package com.example.huitdduru.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.auth.CertifyRequestEntity
import com.example.domain.entity.auth.RegisterRequestEntity
import com.example.domain.entity.auth.SendCodeRequestEntity
import com.example.domain.usecase.auth.CertifyUseCase
import com.example.domain.usecase.auth.RegisterUseCase
import com.example.domain.usecase.auth.SendCodeUseCase
import com.example.huitdduru.util.MutableEventFlow
import com.example.huitdduru.util.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val sendCodeUseCase: SendCodeUseCase,
    private val certifyUseCase: CertifyUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    var name: String = ""
    var email: String = ""
    var password: String = ""
    var intro: String? = null
    var file: MultipartBody.Part? = null

    fun register() {
        viewModelScope.launch {
            kotlin.runCatching {
                registerUseCase.invoke(RegisterRequestEntity(
                    name,
                    email,
                    password,
                    intro!!,
                    file!!))
            }.onSuccess {
                event(Event.SuccessRegister(true))
            }.onFailure {
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

            }
        }
    }

    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }



    sealed class Event {
        data class SuccessRegister(var state: Boolean = false) : Event()
        data class SuccessEmailSend(var state: Boolean = false) : Event()
        data class SuccessEmailCertify(var state: Boolean = false) : Event()
        data class ErrorMessage(val errorMessage: String) : Event()
        object BadRequest: Event()
        object NotFound: Event()
        object Conflict: Event()
    }
}