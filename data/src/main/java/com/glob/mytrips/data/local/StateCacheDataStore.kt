package com.glob.mytrips.data.local

import com.glob.mytrips.data.mappers.datatodto.StateDataToDtoMapper
import com.glob.mytrips.data.mappers.entitytodata.StateEntityToDataMapper
import com.glob.mytrips.data.model.StateData
import com.glob.mytrips.data.repositories.datastore.StateDataStore
import com.glob.mytrips.data.repositories.datastore.cache.StateCache
import com.glob.mytrips.domain.dtos.StateDto
import io.reactivex.Completable
import io.reactivex.Single

class StateCacheDataStore(
    private val stateCache: StateCache,
    private val stateEntityToData: StateEntityToDataMapper,
    private val stateDataToDto: StateDataToDtoMapper
) : StateDataStore {
    override fun getStates(idCountry: Int): Single<List<StateData>> {
        return stateCache.getState(idCountry)
            .map { statesEntity ->
                statesEntity.map { stateEntityToData.transform(it) }
            }
    }

    override fun saveState(state: StateDto): Completable {
        val countryData = stateDataToDto.reverseTransform(state)
        return stateCache.saveState(stateEntityToData.reverseTransform(countryData))
            .doOnComplete {
                println("State Saved!!! --")
            }
    }

    override fun saveStates(states: List<StateDto>): Completable {
        val stateData =
            states.map { stateDataToDto.reverseTransform(it) }
        return stateCache.saveStates(
            stateData.map {
                stateEntityToData.reverseTransform(it)
            }
        ).doOnComplete {
            println("States Saved!!!!!------")
        }
    }

    override fun isCached(idCountry: Int): Single<Boolean> {
        return stateCache.isCached(idCountry)
    }
}