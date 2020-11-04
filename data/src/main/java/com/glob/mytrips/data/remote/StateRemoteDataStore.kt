package com.glob.mytrips.data.remote

import com.glob.mytrips.data.mappers.responsetodata.StateResponseToDataMapper
import com.glob.mytrips.data.model.StateData
import com.glob.mytrips.data.repositories.datastore.StateDataStore
import com.glob.mytrips.data.repositories.datastore.remote.StateRemote
import com.glob.mytrips.domain.dtos.StateDto
import io.reactivex.Completable
import io.reactivex.Single

class StateRemoteDataStore(
    private val stateRemote: StateRemote,
    private val stateRespToData: StateResponseToDataMapper
) : StateDataStore {

    override fun getStates(idCountry: Int): Single<List<StateData>> {
        return stateRemote.getStates(idCountry)
            .map { stateResp ->
                stateResp.map { stateRespToData.transform(it) }
            }
    }

    override fun saveState(state: StateDto): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveStates(states: List<StateDto>): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(idCountry: Int): Single<Boolean> {
        throw UnsupportedOperationException()
    }

}