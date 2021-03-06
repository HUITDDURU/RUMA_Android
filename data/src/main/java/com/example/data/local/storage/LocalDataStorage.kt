package com.example.data.local.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataStorage @Inject constructor(
    private val context: Context
) {

    private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    private val DIARY_ID_KEY = intPreferencesKey("diary_id")

    suspend fun setAccessToken(data: String) {
        context.dataStore.edit {
            it[ACCESS_TOKEN_KEY] = "Bearer $data"
        }
    }

    suspend fun getAccessToken() : String? =
        try {
            val preferences = context.dataStore.data.first()
            preferences[ACCESS_TOKEN_KEY] ?: "Access token not found"
        } catch (e: Exception){
            e.printStackTrace()
            null
        }

    suspend fun setRefreshToken(data: String) {
        context.dataStore.edit {
            it[REFRESH_TOKEN_KEY] = "Bearer $data"
        }
    }

    suspend fun getRefreshToken() : String? =
        try {
            val preferences = context.dataStore.data.first()
            preferences[REFRESH_TOKEN_KEY] ?: "Refresh token not found"
        } catch (e: Exception){
            e.printStackTrace()
            null
        }

    suspend fun setDiaryId(data: Int) {
        context.dataStore.edit {
            it[DIARY_ID_KEY] = data
        }
    }

    suspend fun getDiaryId(): Int =
        try {
            val preferences = context.dataStore.data.first()
            preferences[DIARY_ID_KEY] ?: 0
        } catch (e: Exception){
            e.printStackTrace()
            0
        }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "huitdduru")
