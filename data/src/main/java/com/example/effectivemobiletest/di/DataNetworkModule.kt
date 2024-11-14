package com.example.effectivemobiletest.di

import com.example.effectivemobiletest.data.remote.retrofit.JobApi
import com.example.effectivemobiletest.utils.network.adapter.EitherNewsAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://drive.usercontent.google.com/"

internal val dataNetworkModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(get<EitherNewsAdapterFactory>())
            .client(get<OkHttpClient>())
            .build()
    }

    single <OkHttpClient>{
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single<JobApi> { get<Retrofit>().create(JobApi::class.java) }
    single { EitherNewsAdapterFactory() }
}