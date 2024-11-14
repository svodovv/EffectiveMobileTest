package com.example.effectivemobiletest.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletest.presentation.state.SearchState
import com.example.effectivemobiletest.presentation.utils.DeclensionUtils
import com.example.effectivemobiletest.utils.network.handler.Either
import com.example.effectivemoviletest.domain.model.VacancyModel
import com.example.effectivemoviletest.domain.usecase.DeleteFavoriteVacancyUseCase
import com.example.effectivemoviletest.domain.usecase.GetOffersUseCase
import com.example.effectivemoviletest.domain.usecase.GetVacanciesUseCase
import com.example.effectivemoviletest.domain.usecase.SetFavoriteVacancyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val setFavoriteVacancyUseCase: SetFavoriteVacancyUseCase,
    private val deleteFavoriteVacancyUseCase: DeleteFavoriteVacancyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState.initial)
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                getOffersUseCase(), getVacanciesUseCase()
            ) { offer, vacancy ->
                offer to vacancy
            }.collect { (offer, vacancy) ->

                when {
                    offer is Either.Success && vacancy is Either.Success -> {
                        val vacancyCountDeclension = DeclensionUtils.getVacancyDeclension(vacancy.data.size)

                        _state.update {
                            it.copy(
                                offerList = offer.data,
                                _vacancyList = vacancy.data,
                                vacancyCountDeclension = vacancyCountDeclension,
                                error = false,
                                loading = false
                            )
                        }
                        val a = state.value.vacancyList.map { it.lookingNumber }
                        Log.e("lookingN", a.toString())
                    }

                    offer is Either.Failure || vacancy is Either.Failure -> {
                        _state.update { it.copy(error = true, loading = false) }
                    }
                }
            }
        }
    }

    fun setLimitedData() {
        _state.update { it.copy(isLimitedData = true) }
    }

    fun setFullData() {
        _state.update { it.copy(isLimitedData = false) }
    }

    fun heartClick(vacancyId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val favoriteVacancy: VacancyModel = state.value.vacancyList.first { vacancyId == it.id }

            if (favoriteVacancy.isFavorite) deleteFavoriteVacancyUseCase(favoriteVacancy.id)
            else setFavoriteVacancyUseCase(favoriteVacancy)
        }


    }

}