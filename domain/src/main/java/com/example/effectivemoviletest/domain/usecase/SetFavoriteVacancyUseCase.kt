package com.example.effectivemoviletest.domain.usecase

import com.example.effectivemoviletest.domain.model.VacancyModel
import com.example.effectivemoviletest.domain.repository.FavoriteRepository
import kotlin.math.truncate

class SetFavoriteVacancyUseCase (
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(vacancyModel: VacancyModel) =
        favoriteRepository.setFavoriteVacancy(vacancyModel.copy(isFavorite = true))
}