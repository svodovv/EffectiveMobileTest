package com.example.effectivemobiletest.di

import com.example.effectivemobiletest.data.local.pref.InitializeDatabase
import org.koin.dsl.module

internal val dataStoreModule = module {
    single { InitializeDatabase( get() ) }
}