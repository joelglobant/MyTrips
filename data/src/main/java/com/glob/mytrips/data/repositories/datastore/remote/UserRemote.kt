package com.glob.mytrips.data.repositories.datastore.remote

import com.glob.mytrips.data.remote.response.UserResponse
import io.reactivex.Single

interface UserRemote {
    fun getUserInfo(): Single<UserResponse>
}