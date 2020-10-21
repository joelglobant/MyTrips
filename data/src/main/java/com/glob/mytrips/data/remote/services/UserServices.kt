package com.glob.mytrips.data.remote.services

import com.glob.mytrips.data.remote.response.UserResponse
import com.glob.mytrips.domain.dtos.UserDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserServices {

    @GET("www.google.com/{token}")
    fun getUser(@Path("token") token: Long): Single<Response<UserResponse>>

    @PUT("www.google.com{id}")
    fun saveUserByUser(@Path("id") idUser: Int, @Body userDto: UserDto): Single<Response<Boolean>>
}