package com.glob.mytrips.data.data

import com.glob.mytrips.data.local.StateCacheDataStore
import com.glob.mytrips.data.remote.StateRemoteDataStore
import com.glob.mytrips.data.repositories.datastore.StateDataStore
import com.glob.mytrips.data.repositories.datastore.cache.StateCache

class StateDataStoreFactoryImp(
    private val stateCache: StateCache,
    private val stateRemoteDataStore: StateRemoteDataStore,
    private val stateCachedDataStore: StateCacheDataStore
) : StateDataStoreFactory {
    override fun retrieveDataSource(isCached: Boolean): StateDataStore {
        if (isCached && !stateCache.isExpired()) {
            return retrieveLocalDataSource()
        }
        return retrieveRemoteDataSource()
    }

    override fun retrieveRemoteDataSource(): StateDataStore {
        return stateRemoteDataStore
    }

    override fun retrieveLocalDataSource(): StateDataStore {
        return stateCachedDataStore
    }
}