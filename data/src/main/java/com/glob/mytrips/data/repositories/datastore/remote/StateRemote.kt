package com.glob.mytrips.data.repositories.datastore.remote

import com.glob.mytrips.data.remote.response.StateResponse
import io.reactivex.Single

interface StateRemote {
    fun getStates(idCountry: Int): Single<List<StateResponse>>
}