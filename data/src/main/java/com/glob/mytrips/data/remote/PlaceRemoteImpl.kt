package com.glob.mytrips.data.remote

import com.glob.mytrips.data.remote.response.PlaceResponse
import com.glob.mytrips.data.remote.services.PlaceServices
import com.glob.mytrips.data.repositories.datastore.remote.PlacesRemote
import io.reactivex.Single

class PlaceRemoteImpl(
    private val placeServices: PlaceServices
): PlacesRemote {

    override fun getPlaces(idState: Int): Single<List<PlaceResponse>> {
        return placeServices.getPlaceByState(idState)
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let { placeResponse ->
                        Single.just(placeResponse)
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }
}