package com.example.effectivemobiletest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemoviletest.domain.usecase.GetFavoriteVacancyCountUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NavigationMenuViewModel(
    private val getFavoriteVacancyCountUseCase: GetFavoriteVacancyCountUseCase
) : ViewModel() {

    private val _state = MutableSharedFlow<Int>(0)
    val state = _state.asSharedFlow()

    init {
        init()
    }

    private fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteVacancyCountUseCase().collect { count ->
                _state.emit(count)
            }
        }
    }

}