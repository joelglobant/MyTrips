package com.glob.mytrips.data.remote.services

import com.glob.mytrips.data.remote.response.PhotoResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface PhotoServices {
    @GET("v3/13492dda-432a-461c-b807-5b01e155ee73")
    fun getPhotosByPlace(): Single<Response<List<PhotoResponse>>>
}