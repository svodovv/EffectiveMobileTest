package com.example.effectivemoviletest.domain.di

import com.example.effectivemoviletest.domain.usecase.DeleteFavoriteVacancyUseCase
import com.example.effectivemoviletest.domain.usecase.GetFavoriteVacancyCountUseCase
import com.example.effectivemoviletest.domain.usecase.GetFavoriteVacancyUseCase
import com.example.effectivemoviletest.domain.usecase.GetOffersUseCase
import com.example.effectivemoviletest.domain.usecase.GetVacanciesUseCase
import com.example.effectivemoviletest.domain.usecase.SetFavoriteVacancyUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    factory { GetOffersUseCase(get()) }
    factory { GetVacanciesUseCase(get(), get(), get()) }
    factory { SetFavoriteVacancyUseCase(get()) }
    factory { DeleteFavoriteVacancyUseCase(get()) }
    factory { GetFavoriteVacancyCountUseCase(get() ) }
    factory { GetFavoriteVacancyUseCase ( get() ) }
}
