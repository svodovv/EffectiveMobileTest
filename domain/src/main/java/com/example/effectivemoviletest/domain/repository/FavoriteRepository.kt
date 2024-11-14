package com.example.effectivemoviletest.domain.repository

import com.example.effectivemoviletest.domain.model.VacancyModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    suspend fun setFavoriteVacancy(vacancyList: List<VacancyModel>)
    suspend fun setFavoriteVacancy(vacancy: VacancyModel)

    fun getAllVacancy(): Flow<List<VacancyModel>>
    fun getVacancyIdList(): Flow<List<String>>
    suspend fun deleteVacancyDyId(id: String)

    fun geVacancyCount(): Flow<Int>
}