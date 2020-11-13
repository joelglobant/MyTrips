package com.glob.mytrips.data.remote.services


import com.glob.mytrips.data.remote.response.StateResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StateServices {
    @GET("v3/d0b6e75b-1241-4a8c-9efd-2676746e18ba")
    fun getStatesByCountry(): Single<Response<List<StateResponse>>>
}