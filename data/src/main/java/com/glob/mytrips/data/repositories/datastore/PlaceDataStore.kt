package com.glob.mytrips.data.repositories.datastore

import com.glob.mytrips.data.model.PlaceData
import com.glob.mytrips.domain.dtos.PlaceDto
import io.reactivex.Completable
import io.reactivex.Single

interface PlaceDataStore {
    fun getPlaces(idState: Int): Single<List<PlaceData>>
    fun getPlace(idPlace: Int): Single<PlaceData>
    fun savePlaces(places: List<PlaceDto>): Completable
    fun isCached(idState: Int): Single<Boolean>
}