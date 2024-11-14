package com.example.effectivemobiletest.presentation.state

import com.example.effectivemoviletest.domain.model.VacancyModel

data class FavoriteState(
    val loading: Boolean,
    val error: Boolean,
    val favoriteVacancyList: List<VacancyModel>
){
    companion object{
        val initialize = FavoriteState(
            loading = true,
            error = false,
            favoriteVacancyList = emptyList()
        )
    }
}