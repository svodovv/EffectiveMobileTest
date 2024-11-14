package com.example.effectivemoviletest.domain.usecase

import com.example.effectivemobiletest.utils.network.handler.Either
import com.example.effectivemobiletest.utils.network.handler.ErrorReason
import com.example.effectivemobiletest.utils.network.handler.map
import com.example.effectivemoviletest.domain.model.VacancyModel
import com.example.effectivemoviletest.domain.repository.DatabaseInitializationRepository
import com.example.effectivemoviletest.domain.repository.FavoriteRepository
import com.example.effectivemoviletest.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetVacanciesUseCase(
    private val jobRepository: JobRepository,
    private val initializationRepository: DatabaseInitializationRepository,
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke(): Flow<Either<ErrorReason, List<VacancyModel>>> = flow {

        val dataBaseState = initializationRepository.getDatabaseState()
        val vacancies = jobRepository.getVacancies()

        if (dataBaseState) {
            favoriteRepository.getVacancyIdList().collect { idList ->
                val favoriteVacanciesList = vacancies.map {
                    it.map {
                        replaceFavoriteVacancy(it, idList)
                    }
                }

                emit(favoriteVacanciesList)
            }

        } else {
            val vacanciesListItem = vacancies.map { it.map { replaceFavoriteVacancy(it) } }

            if (vacanciesListItem is Either.Success) {
                vacanciesListItem.data.filter { it.isFavorite }.let {
                    favoriteRepository.setFavoriteVacancy(it)
                }
            }

            initializationRepository.initDatabase()

            favoriteRepository.getVacancyIdList().collect { idList ->
                val favoriteVacanciesList = vacancies.map {
                    it.map {
                        replaceFavoriteVacancy(it, idList)
                    }
                }

                emit(favoriteVacanciesList)
            }
        }


    }

    private fun formatDate(dateString: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, inputFormatter)

        val day = date.dayOfMonth
        val month = date.month

        val monthName =
            month.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale("ru"))

        return "$day $monthName"
    }

    private fun replaceFavoriteVacancy(
        vacancy: VacancyModel, idList: List<String>? = null
    ): VacancyModel {
        val formatDate = formatDate(vacancy.publishedDate)
        val date = "Опубликовано $formatDate"

        if (idList == null)
            return vacancy.copy(publishedDate = date)

        return if (idList.contains(vacancy.id)) {
            vacancy.copy(isFavorite = true, publishedDate = date)
        } else vacancy.copy(isFavorite = false, publishedDate = date)
    }

}