package com.glob.mytrips.data.repositories.datastore.cache

import com.glob.mytrips.data.local.entities.UserEntity
import com.glob.mytrips.data.repositories.datastore.remote.UserRemote
import com.glob.mytrips.domain.dtos.UserDto
import io.reactivex.Completable
import io.reactivex.Single

interface UserCache {
    fun getUser() : Single<UserEntity>
    fun saveUser(user: UserEntity): Completable
    fun isCached(): Single<Boolean>
    fun setLastTime(lastCache: Long)
    fun isExpired(): Boolean
}