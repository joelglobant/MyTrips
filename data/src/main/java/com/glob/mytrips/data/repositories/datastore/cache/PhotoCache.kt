package com.glob.mytrips.data.repositories.datastore.cache

import com.glob.mytrips.data.local.entities.PhotoEntity
import com.glob.mytrips.data.local.entities.PlaceEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PhotoCache {
    fun getPhoto(idPhoto: Int) : Single<PhotoEntity>
    fun savePhoto(photo: PhotoEntity): Completable
    fun isCached(): Single<Boolean>
}