package com.glob.mytrips.data.local

import com.glob.mytrips.data.local.db.MyTripsDb
import com.glob.mytrips.data.local.entities.PhotoEntity
import com.glob.mytrips.data.repositories.datastore.cache.PhotoCache
import io.reactivex.Completable
import io.reactivex.Single

class PhotoCacheImp(
    private val db: MyTripsDb
) : PhotoCache {
    override fun getPhotos(idPlace: Int): Single<List<PhotoEntity>> {
        return Single.just(db.cachedPhotosDao().getPhotosFromPlace(idPlace))
    }

    override fun savePhotos(photos: List<PhotoEntity>): Completable {
        return db.cachedPhotosDao().saveNewPhotos(photos)
    }

    override fun isCached(idPlace: Int): Single<Boolean> {
        return Single.defer {
            Single.just(db.cachedPhotosDao().getPhotosFromPlace(idPlace).isNotEmpty())
        }
    }

    override fun isExpired(): Boolean {
        //TODO("Not yet implemented")
        return false
    }
}