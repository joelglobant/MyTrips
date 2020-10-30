package com.glob.mytrips.data.data

import com.glob.mytrips.data.local.CountryCacheDataStore
import com.glob.mytrips.data.remote.CountryRemoteDataStore
import com.glob.mytrips.data.repositories.datastore.CountryDataStore
import com.glob.mytrips.data.repositories.datastore.cache.CountryCache

class CountryDataStoreFactoryImpl(
    private val countryCache: CountryCache,
    private val countryRemoteDataStore: CountryRemoteDataStore,
    private val countryCacheDataStore: CountryCacheDataStore
) : CountryDataStoreFactory {

    override fun retrieveDataSource(isCached: Boolean): CountryDataStore {
        if (isCached && !countryCache.isExpired()) {
            return retrieveLocalDataSource()
        }
        return retrieveRemoteDataSource()
    }

    override fun retrieveRemoteDataSource(): CountryDataStore {
        return countryRemoteDataStore
    }

    override fun retrieveLocalDataSource(): CountryDataStore {
        return countryCacheDataStore
    }
}