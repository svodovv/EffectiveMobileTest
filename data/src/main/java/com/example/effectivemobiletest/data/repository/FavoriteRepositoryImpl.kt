package com.example.effectivemobiletest.data.repository

import android.util.Log
import com.example.effectivemobiletest.data.local.room.dao.VacancyDAO
import com.example.effectivemobiletest.data.mappers.toVacancyEntity
import com.example.effectivemobiletest.data.mappers.toVacancyModel
import com.example.effectivemoviletest.domain.model.VacancyModel
import com.example.effectivemoviletest.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow
import kotlin.math.truncate

class FavoriteRepositoryImpl(
    private val vacancyDAO: VacancyDAO
) : FavoriteRepository {


    override fun getAllVacancy() = flow {
        vacancyDAO.getVacancies().collect { entityList ->
            val vacancyList = entityList.map {
                val log = it.toVacancyModel()
                Log.e("model", log.toString())
                log
            }

            emit(vacancyList)
        }
    }


    override suspend fun setFavoriteVacancy(vacancyList: List<VacancyModel>) {

        val newVacancyList = vacancyList.map { it.toVacancyEntity() }.toTypedArray()
        vacancyDAO.insertVacancy(*newVacancyList)
    }

    override suspend fun setFavoriteVacancy(vacancy: VacancyModel) {
        vacancyDAO.insertVacancy(vacancy.toVacancyEntity())
    }

    override suspend fun deleteVacancyDyId(id: String) = vacancyDAO.deleteVacancyById(id)

    override fun getVacancyIdList() = vacancyDAO.getVacanciesId()

    override fun geVacancyCount() = vacancyDAO.getVacancyCount()


}