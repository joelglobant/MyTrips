package com.glob.mytrips.data.remote

import com.glob.mytrips.data.remote.response.UserResponse
import com.glob.mytrips.data.remote.services.UserServices
import com.glob.mytrips.data.repositories.datastore.remote.UserRemote
import io.reactivex.Single

class UserRemoteImpl(
    private val userServices: UserServices
) : UserRemote {
    override fun getUserInfo(): Single<UserResponse> {
        return userServices.getCountriesByUser()
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let { userResponse ->
                        Single.just(userResponse)
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }
}