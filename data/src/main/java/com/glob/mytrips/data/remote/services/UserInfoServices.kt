package com.glob.mytrips.data.remote.services

import com.glob.mytrips.data.remote.response.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface UserInfoServices {

    @GET("v3/9c358fa2-9fcb-4e6b-83ec-db56ca201d5c")
    fun getCountriesByUser(): Single<Response<UserResponse>>
}