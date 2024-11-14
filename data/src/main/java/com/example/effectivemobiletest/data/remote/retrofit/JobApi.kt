package com.example.effectivemobiletest.data.remote.retrofit

import com.example.effectivemobiletest.data.remote.dto.JobDTO
import com.example.effectivemobiletest.utils.network.handler.Either
import com.example.effectivemobiletest.utils.network.handler.ErrorReason
import retrofit2.http.GET

interface JobApi {

    @GET("u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getJob(): Either<ErrorReason, JobDTO>

}