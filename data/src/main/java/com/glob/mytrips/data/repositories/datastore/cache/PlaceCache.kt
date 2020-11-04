package com.glob.mytrips.data.repositories.datastore.cache

import com.glob.mytrips.data.local.entities.PlaceEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PlaceCache {
    fun getPlaces(idState: Int) : Single<List<PlaceEntity>>
    fun getPlace(idPlace: Int) : Single<PlaceEntity>
    fun savePlaces(place: List<PlaceEntity>): Completable
    fun isCached(idState: Int): Single<Boolean>
    fun isExpired(): Boolean
}