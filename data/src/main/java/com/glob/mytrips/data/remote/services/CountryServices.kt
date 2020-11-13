package com.glob.mytrips.data.remote.services

import com.glob.mytrips.data.remote.response.CountryResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryServices {
    @GET("v3/473a5514-cf5b-4534-8b2a-583d6c8ed4bd")
    fun getCountriesByUser(): Single<Response<List<CountryResponse>>>
}