package com.example.effectivemobiletest.di

import com.example.effectivemobiletest.presentation.viewmodel.FavoriteViewModel
import com.example.effectivemobiletest.presentation.viewmodel.NavigationMenuViewModel
import com.example.effectivemobiletest.presentation.viewmodel.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchViewModel(get(), get(), get(), get()) }
    viewModel { NavigationMenuViewModel( get() ) }
    viewModel { FavoriteViewModel (get(), get()) }
}