package com.glob.mytrips.data.data

import com.glob.mytrips.data.local.LocalDataStore
import com.glob.mytrips.data.remote.RemoteDataStore
import com.glob.mytrips.data.repositories.datastore.cache.UserCache
import com.glob.mytrips.data.repositories.datastore.UserDataStore

class UserInfoDataStoreFactoryImpl(
    private val userCache: UserCache,
    private val userRemoteDataStore: RemoteDataStore,
    private val userLocalDataStore: LocalDataStore
) : UserInfoDataStoryFactory {

    override fun retrieveDataSource(isCached: Boolean): UserDataStore {
        if (isCached && !userCache.isExpired()) {
            return retrieveLocalDataSource()
        }
        return retrieveRemoteDataSource()
    }

    override fun retrieveRemoteDataSource(): UserDataStore {
        return userRemoteDataStore
    }

    override fun retrieveLocalDataSource(): UserDataStore {
        return userLocalDataStore
    }

}