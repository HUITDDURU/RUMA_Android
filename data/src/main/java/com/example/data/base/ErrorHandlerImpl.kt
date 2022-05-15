package com.example.data.base

import com.example.domain.base.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    override suspend fun<T> invoke(func: suspend () -> T): T =
        try {
            withContext(Dispatchers.IO){
                func.invoke()
            }
        } catch (e: Throwable){
            throw when(e) {
                is HttpException -> when(e.code()) {
                    400 -> BadRequest(e.message())
                    401 -> UnAuthorized(e.message())
                    403 -> Forbidden(e.message())
                    404 -> NotFound(e.message())
                    409 -> Conflict(e.message())
                    500 -> ServerError(e.message())
                    else -> UnknownError(e.message())
                }
                is ConnectException,
                is SocketTimeoutException,
                is UnknownHostException -> NetworkError(e.message)
                else -> UnknownError(e.message)
            }
        }

}