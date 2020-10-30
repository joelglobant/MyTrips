package com.glob.mytrips.data.data

import com.glob.mytrips.data.repositories.datastore.UserDataStore

interface UserInfoDataStoryFactory {
    fun retrieveDataSource(isCached: Boolean): UserDataStore
    fun retrieveRemoteDataSource(): UserDataStore
    fun retrieveLocalDataSource(): UserDataStore
}