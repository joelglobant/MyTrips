package com.glob.mytrips.data.data

import com.glob.mytrips.data.local.PhotoCacheDataStore
import com.glob.mytrips.data.remote.PhotoRemoteDataStore
import com.glob.mytrips.data.repositories.datastore.PhotoDataStore
import com.glob.mytrips.data.repositories.datastore.cache.PhotoCache

class PhotoDataStoreFactoryImp(
    private val photoCached: PhotoCache,
    private val photoRemoteDataStore: PhotoRemoteDataStore,
    private val photoCahceDataStore: PhotoCacheDataStore
) : PhotoDataStoreFactory {
    override fun retrieveDataSource(isCached: Boolean): PhotoDataStore {
        if (isCached && !photoCached.isExpired()) {
            return retrieveLocalDataSource()
        }
        return retrieveRemoteDataSource()
    }

    override fun retrieveRemoteDataSource(): PhotoDataStore {
        return photoRemoteDataStore
    }

    override fun retrieveLocalDataSource(): PhotoDataStore {
        return photoCahceDataStore
    }
}