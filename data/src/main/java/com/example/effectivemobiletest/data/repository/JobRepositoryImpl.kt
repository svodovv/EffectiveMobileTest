package com.example.effectivemobiletest.data.repository

import com.example.effectivemobiletest.data.remote.dto.JobDTO
import com.example.effectivemobiletest.data.remote.dto.JobDTO.Companion.toOfferModel
import com.example.effectivemobiletest.data.remote.dto.JobDTO.Companion.toVacancyModel
import com.example.effectivemobiletest.data.remote.retrofit.JobApi
import com.example.effectivemoviletest.domain.model.OfferModel
import com.example.effectivemoviletest.domain.model.VacancyModel
import com.example.effectivemoviletest.domain.repository.JobRepository
import com.example.effectivemobiletest.utils.network.handler.Either
import com.example.effectivemobiletest.utils.network.handler.ErrorReason
import com.example.effectivemobiletest.utils.network.handler.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


class JobRepositoryImpl(
    private val jobApi: JobApi
): JobRepository {

    private var cachedJob: Either<ErrorReason, JobDTO>? = null
    private val lock = Mutex()

    override suspend fun getOffers(): Either<ErrorReason, List<OfferModel>> {
        return getCachedJob().map { it.toOfferModel() }
    }

    override suspend fun getVacancies(): Either<ErrorReason, List<VacancyModel>> {
        return getCachedJob().map { it.toVacancyModel() }
    }

    private suspend fun getCachedJob(): Either<ErrorReason, JobDTO> {
         return lock.withLock {
            cachedJob ?: fetchJob().also { cachedJob = it }
        }
    }

    private suspend fun fetchJob(): Either<ErrorReason, JobDTO> {
        return jobApi.getJob()
    }

}