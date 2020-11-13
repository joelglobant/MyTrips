package com.glob.mytrips.data.remote.services

import com.glob.mytrips.data.remote.response.PlaceResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceServices {
    @GET("v3/c8101b4d-36f3-4e0c-8e5a-e0d8de4e01e5")
    fun getPlaceByState(): Single<Response<List<PlaceResponse>>>

    @GET("v3/9182e4bc-5561-4af2-9dfd-78ab0ffde218") //only one places, I need a owen api to get places by id!
    fun getPlaceById(): Single<Response<PlaceResponse>>
}