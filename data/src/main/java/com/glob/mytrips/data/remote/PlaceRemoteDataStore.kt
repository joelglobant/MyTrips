package com.glob.mytrips.data.remote

import com.glob.mytrips.data.mappers.responsetodata.PlaceResponseToDataMapper
import com.glob.mytrips.data.model.PlaceData
import com.glob.mytrips.data.repositories.datastore.PlaceDataStore
import com.glob.mytrips.data.repositories.datastore.remote.PlacesRemote
import com.glob.mytrips.domain.dtos.PlaceDto
import io.reactivex.Completable
import io.reactivex.Single

class PlaceRemoteDataStore(
    private val placeRemote: PlacesRemote,
    private val placeRespToData: PlaceResponseToDataMapper
) : PlaceDataStore {
    override fun getPlaces(idState: Int): Single<List<PlaceData>> {
        return placeRemote.getPlaces(idState)
            .flatMap { places ->
                return@flatMap Single.just(places.map { placeRespToData.transform(it) })
            }
    }

    override fun getPlace(idPlace: Int): Single<PlaceData> {
        return placeRemote.getPlace(idPlace)
            .flatMap {
                return@flatMap Single.just(placeRespToData.transform(it))
            }
    }

    override fun savePlaces(places: List<PlaceDto>): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(idState: Int): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}