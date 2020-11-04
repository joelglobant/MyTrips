package com.glob.mytrips.data.data

import com.glob.mytrips.data.local.PlaceCacheDataStore
import com.glob.mytrips.data.remote.PlaceRemoteDataStore
import com.glob.mytrips.data.repositories.datastore.PlaceDataStore
import com.glob.mytrips.data.repositories.datastore.cache.PlaceCache

class PlaceDataStoreFactoryImp(
    private val placeCached: PlaceCache,
    private val placeRemoteDataStore: PlaceRemoteDataStore,
    private val placeCacheDataStore: PlaceCacheDataStore
): PlaceDataStoreFactory {
    override fun retrieveDataSource(isCached: Boolean): PlaceDataStore {
        if (isCached && !placeCached.isExpired()) {
            return retrieveLocalDataSource()
        }
        return retrieveRemoteDataSource()
    }

    override fun retrieveRemoteDataSource(): PlaceDataStore {
        return placeRemoteDataStore
    }

    override fun retrieveLocalDataSource(): PlaceDataStore {
        return placeCacheDataStore
    }
}