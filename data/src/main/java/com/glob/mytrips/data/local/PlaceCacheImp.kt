package com.glob.mytrips.data.local

import com.glob.mytrips.data.local.db.MyTripsDb
import com.glob.mytrips.data.local.entities.PlaceEntity
import com.glob.mytrips.data.repositories.datastore.cache.PlaceCache
import io.reactivex.Completable
import io.reactivex.Single

class PlaceCacheImp(
    private val db: MyTripsDb
): PlaceCache {
    override fun getPlaces(idState: Int): Single<List<PlaceEntity>> {
        return Single.just(db.cachedPlacesDao().getPlacesFromState(idState))
    }

    override fun getPlace(idPlace: Int): Single<PlaceEntity> {
        return Single.just(db.cachedPlacesDao().getPlaceById(idPlace))
    }

    override fun savePlaces(places: List<PlaceEntity>): Completable {
        return db.cachedPlacesDao().saveNewPlaces(places)
    }

    override fun isCached(idState: Int): Single<Boolean> {
        return Single.defer {
            Single.just(db.cachedPlacesDao().getPlacesFromState(idState).isNotEmpty())
        }
    }

    override fun isExpired(): Boolean {
        //TODO("Not yet implemented")
        return false
    }

}