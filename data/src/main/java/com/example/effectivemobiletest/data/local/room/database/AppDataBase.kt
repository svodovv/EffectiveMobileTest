package com.example.effectivemobiletest.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.effectivemobiletest.data.local.room.dao.VacancyDAO
import com.example.effectivemobiletest.data.local.room.entity.VacancyEntity

@Database(entities = [VacancyEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun vacancyDao(): VacancyDAO

}
