package com.example.effectivemoviletest.domain.usecase

import com.example.effectivemobiletest.utils.network.handler.map
import com.example.effectivemoviletest.domain.repository.JobRepository
import kotlinx.coroutines.flow.flow

class GetOffersUseCase(
    private val jobRepository: JobRepository
) {
    operator fun invoke() = flow {

        val offers = jobRepository.getOffers().map {
            it.map { offer ->
                offer.copy(title = offer.title.trimStart())
            }
        }
        emit(offers)
    }
}