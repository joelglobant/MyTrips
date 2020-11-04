package com.glob.mytrips.data.local

import com.glob.mytrips.data.local.db.MyTripsDb
import com.glob.mytrips.data.local.entities.StateEntity
import com.glob.mytrips.data.repositories.datastore.cache.StateCache
import io.reactivex.Completable
import io.reactivex.Single

class StateCacheImp(
    private val db: MyTripsDb
) : StateCache {
    override fun getState(idCountry: Int): Single<List<StateEntity>> {
        return Single.just(db.cachedStatesDao().getStatesFromCountry(idCountry))
    }

    override fun saveState(state: StateEntity): Completable {
        return db.cachedStatesDao().saveNewState(state)
    }

    override fun saveStates(states: List<StateEntity>): Completable {
        return db.cachedStatesDao().saveNewStates(states)
    }

    override fun isCached(idCountry: Int): Single<Boolean> {
        return Single.defer {
            Single.just(db.cachedStatesDao().getStatesFromCountry(idCountry).isNotEmpty())
        }
    }

    override fun isExpired(): Boolean {
        //TODO("Not yet implemented")
        return false
    }
}