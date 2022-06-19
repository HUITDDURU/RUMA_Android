package com.example.data.datasource

import com.example.domain.entity.match.ErrorResponseEntity
import com.example.domain.entity.user.UserInfoResponseEntity
import com.google.gson.Gson
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import org.json.JSONObject
import javax.inject.Inject

class SocketDataSourceImpl @Inject constructor(
    private val socket: Socket
): SocketDataSource{

    private val _receiveMessage = MutableSharedFlow<HashMap>

    override suspend fun connect() {
        socket.connect()
    }

    override suspend fun disconnect() {
        socket.disconnect()
    }

    override suspend fun matching() {
        socket.on("matching.start", null)
    }

    override suspend fun cancel() {
        socket.on("matching.cancel", onMessage)
    }

    override suspend fun accept(accept: Boolean) {
        val data = JSONObject()
        data.put("accept", accept)
        socket.emit("matching.accept", data)
    }

    override suspend fun localMatching(code: String) {
        TODO("Not yet implemented")
    }

    override suspend fun userInfo(): UserInfoResponseEntity {
        socket.on("success")
    }

    override suspend fun unexpectedCancel(): SharedFlow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun success(): SharedFlow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun error(): ErrorResponseEntity {
        TODO("Not yet implemented")
    }

    private val onMessage = Emitter.Listener { args ->
        val json = args[0].toString()
    }

    private val onError = Emitter.Listener { args ->
        val json = args[0].toString()
        Gson().fromJson(json, ErrorResponseEntity::class.java)
    }

    private val onUserInfo = Emitter.Listener { args ->
        val json = args[0].toString()
        Gson().fromJson(json, UserInfoResponseEntity::class.java)
    }
}