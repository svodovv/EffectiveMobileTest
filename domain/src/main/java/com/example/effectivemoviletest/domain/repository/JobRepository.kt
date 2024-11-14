package com.example.effectivemoviletest.domain.repository

import com.example.effectivemobiletest.utils.network.handler.Either
import com.example.effectivemobiletest.utils.network.handler.ErrorReason
import com.example.effectivemoviletest.domain.model.OfferModel
import com.example.effectivemoviletest.domain.model.VacancyModel

interface JobRepository {
    suspend fun getOffers(): Either<ErrorReason, List<OfferModel>>
    suspend fun getVacancies(): Either<ErrorReason, List<VacancyModel>>
}