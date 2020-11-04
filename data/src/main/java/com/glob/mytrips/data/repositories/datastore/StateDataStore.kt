package com.glob.mytrips.data.repositories.datastore

import com.glob.mytrips.data.model.StateData
import com.glob.mytrips.domain.dtos.StateDto
import io.reactivex.Completable
import io.reactivex.Single

interface StateDataStore {
    fun getStates(idCountry: Int): Single<List<StateData>>
    fun saveState(state: StateDto): Completable
    fun saveStates(states: List<StateDto>): Completable
    fun isCached(idCountry: Int): Single<Boolean>
}