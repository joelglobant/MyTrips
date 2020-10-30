package com.glob.mytrips.data.repositories.datastore.cache

import com.glob.mytrips.data.local.entities.StateEntity
import io.reactivex.Completable
import io.reactivex.Single

interface StateCache {
    fun getState(idCountry: Int) : Single<StateEntity>
    fun saveState(state: StateEntity): Completable
    fun isCached(): Single<Boolean>
}