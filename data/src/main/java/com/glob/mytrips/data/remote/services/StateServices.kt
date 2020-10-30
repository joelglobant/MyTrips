package com.glob.mytrips.data.remote.services


import com.glob.mytrips.data.remote.response.StateResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StateServices {
    @GET("www.google.com{id}")
    fun getStatesByCountry(@Path("id") idCountry: Int): Single<Response<List<StateResponse>>>
}