package com.glob.mytrips.data.remote.services

import com.glob.mytrips.data.remote.response.UserResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface UserInfoServices {

    @GET("v3/525f3580-fc43-430e-9420-34453b8108be")
    fun getCountriesByUser(): Single<Response<UserResponse>>
}