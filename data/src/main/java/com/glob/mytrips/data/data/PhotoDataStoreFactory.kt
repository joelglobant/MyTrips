package com.glob.mytrips.data.data

import com.glob.mytrips.data.repositories.datastore.PhotoDataStore

interface PhotoDataStoreFactory {
    fun retrieveDataSource(isCached: Boolean): PhotoDataStore
    fun retrieveRemoteDataSource(): PhotoDataStore
    fun retrieveLocalDataSource(): PhotoDataStore
}