package com.example.data.datasource

import com.example.domain.entity.user.UserInfoResponseEntity
import com.google.gson.Gson
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.json.JSONObject
import javax.inject.Inject

class SocketDataSourceImpl @Inject constructor(
    private val socket: Socket
): SocketDataSource{

    private val _receiveMessage = MutableSharedFlow<String>()
    val receiveMessage = _receiveMessage.asSharedFlow()
    private lateinit var userInfo: UserInfoResponseEntity

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
        val data = JSONObject()
        data.put("code", code)
        socket.emit("matching.friend", data)
    }

    override suspend fun userInfo(): UserInfoResponseEntity {
        socket.on("success", onMessage)
        socket.on("cancel", onMessage)
        socket.on("userInfo", onUserInfo)
        return userInfo
    }

    private val onMessage = Emitter.Listener { args ->
        val json = JSONObject(args[0].toString())
        _receiveMessage.tryEmit(json.getString(""))
    }

    private val onUserInfo = Emitter.Listener { args ->
        val json = args[0].toString()
        userInfo = Gson().fromJson(json, UserInfoResponseEntity::class.java)
    }
}