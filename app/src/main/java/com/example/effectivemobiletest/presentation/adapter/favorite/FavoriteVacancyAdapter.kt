package com.example.effectivemobiletest.presentation.adapter.favorite

import com.example.effectivemobiletest.presentation.adapter.common.AdapterDelegate
import com.example.effectivemobiletest.presentation.adapter.common.SearchItem
import com.example.effectivemobiletest.presentation.adapter.common.SearchItemDiffCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class FavoriteVacancyAdapter(
    heartOnClick: (String) -> Unit
) :
    AsyncListDifferDelegationAdapter<SearchItem>(SearchItemDiffCallback){
        init {
            delegatesManager.addDelegate(AdapterDelegate.parentSearchVacancyAdapterDelegate(heartOnClick))
        }
    }