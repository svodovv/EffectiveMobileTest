package com.example.effectivemobiletest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletest.presentation.state.FavoriteState
import com.example.effectivemoviletest.domain.usecase.DeleteFavoriteVacancyUseCase
import com.example.effectivemoviletest.domain.usecase.GetFavoriteVacancyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class FavoriteViewModel(
    private val getFavoriteVacancyUseCase: GetFavoriteVacancyUseCase,
    private val deleteFavoriteVacancyUseCase: DeleteFavoriteVacancyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState.initialize)
    val state = _state.asStateFlow()

    init {
        loadFavoriteVacancy()
    }

    fun loadFavoriteVacancy() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(loading = true) }

            try {
                getFavoriteVacancyUseCase().collect { data ->

                    _state.update {
                        it.copy(
                            error = false, loading = false, favoriteVacancyList = data
                        )
                    }
                }
            }
            catch (e: IOException) {
                _state.update { it.copy(loading = false, error = true) }
            }

        }
    }


    fun deleteFavoriteVacancy(vacancyId :String){
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteVacancyUseCase(vacancyId)
        }
    }

}