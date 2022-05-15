package com.example.domain.base

import java.lang.RuntimeException

data class BadRequest(val msg: String) : RuntimeException()
data class NotFound(val msg: String) : RuntimeException()
data class Conflict(val msg: String) : RuntimeException()
data class UnAuthorized(val msg: String) : RuntimeException()
data class Forbidden(val msg: String) : RuntimeException()
data class UnknownError(val msg: String?) : RuntimeException()
data class ServerError(val msg: String) : RuntimeException()
data class NetworkError(val msg: String?) : RuntimeException()