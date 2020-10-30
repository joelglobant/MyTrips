package com.glob.mytrips.data.repositories.datastore

import com.glob.mytrips.data.local.entities.UserEntity
import com.glob.mytrips.data.model.UserData
import io.reactivex.Completable
import io.reactivex.Single

interface UserDataStore {
    fun getUser(): Single<UserData>
    fun saveUser(user: UserEntity): Completable
    fun isCached(): Single<Boolean>
}