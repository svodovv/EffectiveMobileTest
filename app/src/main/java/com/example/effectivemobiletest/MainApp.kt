package com.example.effectivemobiletest

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.effectivemobiletest.di.appModules
import com.example.effectivemobiletest.di.dataModules
import com.example.effectivemoviletest.domain.di.domainModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val modules = listOf(appModules, dataModules, domainModules).flatten()

        startKoin{
            androidLogger()
            androidContext(this@MainApp)
            modules(modules)
        }
    }
}