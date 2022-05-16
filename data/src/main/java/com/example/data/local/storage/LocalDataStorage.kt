package com.example.data.local.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataStorage @Inject constructor(
    private val context: Context
) {

    private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")

    suspend fun setAccessToken(data: String) {
        context.dataStore.edit {
            it[ACCESS_TOKEN_KEY] = data
        }
    }

    suspend fun setRefreshToken(data: String) {
        context.dataStore.edit {
            it[REFRESH_TOKEN_KEY] = data
        }
    }

    suspend fun getAccessToken(): Flow<String> =
        context.dataStore.data.map {
            it[ACCESS_TOKEN_KEY] ?: "Access token not found"
        }

    suspend fun getRefreshToken(): Flow<String> =
        context.dataStore.data.map {
            it[REFRESH_TOKEN_KEY] ?: "Refresh token not found"
        }

}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "huitdduru")
