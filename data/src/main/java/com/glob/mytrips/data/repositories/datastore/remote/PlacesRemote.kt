package com.glob.mytrips.data.repositories.datastore.remote

import com.glob.mytrips.data.remote.response.PlaceResponse
import io.reactivex.Single

interface PlacesRemote {
    fun getPlaces(idState: Int): Single<List<PlaceResponse>>
}