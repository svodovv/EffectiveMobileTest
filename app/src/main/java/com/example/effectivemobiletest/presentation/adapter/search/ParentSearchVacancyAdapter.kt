package com.example.effectivemobiletest.presentation.adapter.search

import com.example.effectivemobiletest.presentation.adapter.common.AdapterDelegate
import com.example.effectivemobiletest.presentation.adapter.common.SearchItem
import com.example.effectivemobiletest.presentation.adapter.common.SearchItemDiffCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ParentSearchVacancyAdapter(
    btnOnClick: () -> Unit,
    heartOnClick: (String) -> Unit,
) : AsyncListDifferDelegationAdapter<SearchItem>(SearchItemDiffCallback) {
    init {
        delegatesManager
            .addDelegate(AdapterDelegate.parentSearchVacancyAdapterDelegate(heartOnClick))
            .addDelegate(AdapterDelegate.childSearchOfferAdapterDelegate())
            .addDelegate(AdapterDelegate.vacancyButtonAdapterDelegate { btnOnClick() })
    }

}