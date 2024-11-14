package com.example.effectivemobiletest.data.local.pref


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "init_db")

class InitializeDatabase(private val context: Context){

    companion object{
        val IS_DATABASE_INITIALIZED = booleanPreferencesKey("is_database_initialized")
    }

    val getDatabaseState: Flow<Boolean> = context.dataStore.data
        .map { pref ->
            pref[IS_DATABASE_INITIALIZED] ?: false
        }

    suspend fun setDatabaseState(){
        context.dataStore.edit { pref ->
            pref[IS_DATABASE_INITIALIZED] = true
        }
    }
}