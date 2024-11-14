package com.example.effectivemobiletest.presentation.state

import com.example.effectivemoviletest.domain.model.OfferModel
import com.example.effectivemoviletest.domain.model.VacancyModel

data class SearchState(
    val loading: Boolean,
    val error: Boolean,
    private val _vacancyList: List<VacancyModel>,
    val offerList: List<OfferModel>,
    val vacancyCountDeclension: String?,
    val isLimitedData: Boolean
){
    val vacancyList get() =
        if (isLimitedData) _vacancyList.take(3)
        else _vacancyList

    companion object{
        val initial = SearchState(
            loading = true,
            error = false,
            _vacancyList = emptyList(),
            offerList = emptyList(),
            vacancyCountDeclension = null,
            isLimitedData = true
        )
    }

}
