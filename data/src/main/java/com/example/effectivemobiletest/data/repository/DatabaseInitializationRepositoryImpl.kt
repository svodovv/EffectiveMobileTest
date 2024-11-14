package com.example.effectivemobiletest.data.repository

import com.example.effectivemobiletest.data.local.pref.InitializeDatabase
import com.example.effectivemoviletest.domain.repository.DatabaseInitializationRepository
import kotlinx.coroutines.flow.first

class DatabaseInitializationRepositoryImpl(
    private val initializeDatabase: InitializeDatabase
): DatabaseInitializationRepository {

    override suspend fun getDatabaseState(): Boolean {
        return initializeDatabase.getDatabaseState.first()
    }

    override suspend fun initDatabase(){
        initializeDatabase.setDatabaseState()
    }

}