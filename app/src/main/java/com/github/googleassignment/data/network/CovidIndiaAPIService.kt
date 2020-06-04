package com.github.googleassignment.data.network

import com.github.googleassignment.data.network.model.State
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/**
 * Created by swayangjit on 30/5/20.
 */

private const val BASE_URL = "https://api.covid19india.org/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface CovidIndiaAPIService {

    @GET("v2/state_district_wise.json")
    fun getAllStateInfo(): Deferred<List<State>>
}

object CovidIndiaApi {
    val covidIndiaApiService: CovidIndiaAPIService by lazy { retrofit.create(CovidIndiaAPIService::class.java) }
}