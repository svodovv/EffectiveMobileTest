package com.example.effectivemobiletest.data.local.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.effectivemobiletest.data.local.room.entity.VacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDAO {

    @Query("SELECT * FROM vacancy")
    fun getVacancies(): Flow<List<VacancyEntity>>


    @Query("SELECT id FROM vacancy")
    fun getVacanciesId(): Flow<List<String>>

    @Upsert
    suspend fun insertVacancy(vararg vacancy: VacancyEntity)

    @Query("DELETE FROM vacancy WHERE id = :id")
    suspend fun deleteVacancyById(id: String)

    @Query("SELECT COUNT(*) FROM vacancy")
    fun getVacancyCount(): Flow<Int>

}