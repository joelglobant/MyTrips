package com.glob.mytrips.data.remote

import com.glob.mytrips.data.remote.response.StateResponse
import com.glob.mytrips.data.remote.services.StateServices
import com.glob.mytrips.data.repositories.datastore.remote.StateRemote
import io.reactivex.Single

class StateRemoteImpl(
    private val statesServices: StateServices
): StateRemote {
    override fun getStates(idCountry: Int): Single<List<StateResponse>> {
        return statesServices.getStatesByCountry(idCountry)
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let { stateResponse ->
                        Single.just(stateResponse)
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }
}