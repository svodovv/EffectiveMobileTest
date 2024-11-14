package com.example.effectivemoviletest.domain.repository

interface DatabaseInitializationRepository {
    suspend fun getDatabaseState(): Boolean
    suspend fun initDatabase()
}