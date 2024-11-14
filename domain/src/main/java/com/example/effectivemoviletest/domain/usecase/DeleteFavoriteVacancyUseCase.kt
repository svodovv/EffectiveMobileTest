package com.example.effectivemoviletest.domain.usecase

import com.example.effectivemoviletest.domain.repository.FavoriteRepository

class DeleteFavoriteVacancyUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(vacancyId: String){
        favoriteRepository.deleteVacancyDyId(id = vacancyId)
    }
}