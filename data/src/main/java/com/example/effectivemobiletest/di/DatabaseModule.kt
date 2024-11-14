package com.example.effectivemobiletest.di

import androidx.room.Dao
import androidx.room.Room
import com.example.effectivemobiletest.data.local.room.dao.VacancyDAO
import com.example.effectivemobiletest.data.local.room.database.AppDataBase
import org.koin.dsl.module


internal val databaseModule = module {
    single<AppDataBase> { Room.databaseBuilder(
        get(), AppDataBase::class.java, "vacancy_database").build()
    }

    single<VacancyDAO> { get<AppDataBase>().vacancyDao() }
}