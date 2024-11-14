package com.example.effectivemobiletest.presentation.adapter.common

import com.example.effectivemoviletest.domain.model.OfferModel
import com.example.effectivemoviletest.domain.model.VacancyModel

sealed class SearchItem {
    data class OfferItemList(val offerList: List<OfferModel>): SearchItem()
    data class JobVacancyItem(val vacancy: VacancyModel): SearchItem()
    data class ButtonItem(val text: String): SearchItem()

}


