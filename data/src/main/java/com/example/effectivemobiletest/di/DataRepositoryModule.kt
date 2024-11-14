package com.example.effectivemobiletest.di

import com.example.effectivemobiletest.data.repository.DatabaseInitializationRepositoryImpl
import com.example.effectivemobiletest.data.repository.FavoriteRepositoryImpl
import com.example.effectivemobiletest.data.repository.JobRepositoryImpl
import com.example.effectivemoviletest.domain.repository.DatabaseInitializationRepository
import com.example.effectivemoviletest.domain.repository.FavoriteRepository
import com.example.effectivemoviletest.domain.repository.JobRepository
import org.koin.dsl.module

internal val dataRepositoryModule = module {
    single<JobRepository> { JobRepositoryImpl(get()) }

    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }

    single<DatabaseInitializationRepository> { DatabaseInitializationRepositoryImpl(get()) }
}