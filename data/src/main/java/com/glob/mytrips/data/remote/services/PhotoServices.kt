package com.glob.mytrips.data.remote.services

import com.glob.mytrips.data.remote.response.PhotoResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface PhotoServices {
    @GET("v3/a3afa21c-ebc6-42da-a8df-b4558340f09d")
    fun getPhotosByPlace(idPlace: Int): Single<Response<List<PhotoResponse>>>
}