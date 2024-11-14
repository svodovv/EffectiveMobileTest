package com.example.effectivemoviletest.domain.usecase

import com.example.effectivemoviletest.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetFavoriteVacancyUseCase(
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke() = flow {
        favoriteRepository.getAllVacancy().collect {
            val favoriteVacancyList =
                it.map { it }

            emit(favoriteVacancyList)
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
}