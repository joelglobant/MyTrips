package com.glob.mytrips.data.local

import com.glob.mytrips.data.local.entities.UserEntity
import com.glob.mytrips.data.mappers.entitytodata.UserEntityToDataMapper
import com.glob.mytrips.data.model.UserData
import com.glob.mytrips.data.repositories.datastore.cache.UserCache
import com.glob.mytrips.data.repositories.datastore.UserDataStore
import io.reactivex.Completable
import io.reactivex.Single

class LocalDataStore(
    private val userCache: UserCache,
    private val userMapper: UserEntityToDataMapper
) : UserDataStore {

    override fun getUser(): Single<UserData> {
        return userCache.getUser()
            .map {
                userMapper.transform(it)
            }
    }

    override fun isCached(): Single<Boolean> {
        return userCache.isCached()
    }

    override fun saveUser(user: UserEntity): Completable {
        return userCache.saveUser(user)
            .doOnComplete {
                userCache.setLastTime(System.currentTimeMillis())
                // TODO: 28/10/2020 remove next line code!
                println(System.currentTimeMillis().toString())
            }
    }
}