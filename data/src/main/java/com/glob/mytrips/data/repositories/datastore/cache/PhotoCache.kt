package com.glob.mytrips.data.repositories.datastore.cache

import com.glob.mytrips.data.local.entities.PhotoEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PhotoCache {
    fun getPhotos(idPlace: Int) : Single<List<PhotoEntity>>
    fun savePhotos(photos: List<PhotoEntity>): Completable
    fun isCached(idPlace: Int): Single<Boolean>
    fun isExpired(): Boolean
}