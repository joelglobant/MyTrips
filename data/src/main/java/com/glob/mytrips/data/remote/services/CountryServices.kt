package com.glob.mytrips.data.remote.services

import com.glob.mytrips.data.remote.response.CountryResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryServices {
    @GET("www.google.com{id}")
    fun getCountriesByUser(@Path("id") idUser: Int): Single<Response<List<CountryResponse>>>
}