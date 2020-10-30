package com.glob.mytrips.data.repositories.datastore.cache

import com.glob.mytrips.data.local.entities.PlaceEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PlaceCache {
    fun getPlace(idState: Int) : Single<PlaceEntity>
    fun savePlace(place: PlaceEntity): Completable
    fun isCached(): Single<Boolean>
}