package com.glob.mytrips.data.data

import com.glob.mytrips.data.repositories.datastore.PlaceDataStore

interface PlaceDataStoreFactory {
    fun retrieveDataSource(isCached: Boolean): PlaceDataStore
    fun retrieveRemoteDataSource(): PlaceDataStore
    fun retrieveLocalDataSource(): PlaceDataStore
}