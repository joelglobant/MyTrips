package com.glob.mytrips.data.data

import com.glob.mytrips.data.repositories.datastore.StateDataStore

interface StateDataStoreFactory {
    fun retrieveDataSource(isCached: Boolean): StateDataStore
    fun retrieveRemoteDataSource(): StateDataStore
    fun retrieveLocalDataSource(): StateDataStore
}