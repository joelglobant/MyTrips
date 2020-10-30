package com.glob.mytrips.data.data

import com.glob.mytrips.data.repositories.datastore.CountryDataStore

interface CountryDataStoreFactory {
    fun retrieveDataSource(isCached: Boolean): CountryDataStore
    fun retrieveRemoteDataSource(): CountryDataStore
    fun retrieveLocalDataSource(): CountryDataStore
}