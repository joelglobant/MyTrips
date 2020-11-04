package com.glob.mytrips.data.local

import com.glob.mytrips.data.mappers.datatodto.PlaceDataToDtoMapper
import com.glob.mytrips.data.mappers.entitytodata.PlaceEntityToDataMapper
import com.glob.mytrips.data.model.PlaceData
import com.glob.mytrips.data.repositories.datastore.PlaceDataStore
import com.glob.mytrips.data.repositories.datastore.cache.PlaceCache
import com.glob.mytrips.domain.dtos.PlaceDto
import io.reactivex.Completable
import io.reactivex.Single

class PlaceCacheDataStore(
    private val placeCached: PlaceCache,
    private val placeDataToDto: PlaceDataToDtoMapper,
    private val placeEntityToDto: PlaceEntityToDataMapper
): PlaceDataStore {
    override fun getPlaces(idState: Int): Single<List<PlaceData>> {
        return placeCached.getPlaces(idState)
            .map { placesEnt ->
                placesEnt.map {
                    placeEntityToDto.transform(it)
                }
            }
    }

    override fun getPlace(idPlace: Int): Single<PlaceData> {
        return placeCached.getPlace(idPlace)
            .map {
                placeEntityToDto.transform(it)
            }
    }

    override fun savePlaces(places: List<PlaceDto>): Completable {
        val placesData = places.map { placeDataToDto.reverseTransform(it) }
        return placeCached.savePlaces(
            placesData.map { placeEntityToDto.reverseTransform(it) }
        ).doOnComplete {
            println("Places Saved!!!!")
        }
    }

    override fun isCached(idState: Int): Single<Boolean> {
        return placeCached.isCached(idState)
    }
}