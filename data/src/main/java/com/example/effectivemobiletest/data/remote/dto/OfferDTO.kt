package com.example.effectivemobiletest.data.remote.dto

import com.example.effectivemoviletest.domain.model.OfferModel

data class OfferDTO(
    val button: ButtonDTO?,
    val id: String?,
    val link: String,
    val title: String
)
