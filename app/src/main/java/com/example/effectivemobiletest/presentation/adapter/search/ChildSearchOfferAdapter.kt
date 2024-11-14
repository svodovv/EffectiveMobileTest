package com.example.effectivemobiletest.presentation.adapter.search

import com.example.effectivemobiletest.presentation.adapter.common.AdapterDelegate.childSearchOfferItemAdapterDelegate
import com.example.effectivemoviletest.domain.model.OfferModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class ChildSearchOfferAdapter : ListDelegationAdapter<List<OfferModel>>() {
    init {
        delegatesManager.addDelegate(childSearchOfferItemAdapterDelegate())
    }
}

