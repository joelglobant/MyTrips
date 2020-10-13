package com.glob.mytrips.data.remote.services

import com.glob.mytrips.data.remote.response.PlaceResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceServices {

    @GET("www.google.com{id}")
    fun getPlaceDetail(@Path("id") idPlace: Int): Single<Response<PlaceResponse>>

    @GET("www.google.com{id}")
    fun getPlacesByUser(@Path("id") idUser: Int): Single<Response<List<PlaceResponse>>>
}