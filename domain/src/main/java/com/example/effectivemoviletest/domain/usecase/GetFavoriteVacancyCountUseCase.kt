package com.example.effectivemoviletest.domain.usecase

import com.example.effectivemoviletest.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.flow

class GetFavoriteVacancyCountUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke() = favoriteRepository.geVacancyCount()
}
