package com.glob.mytrips.data.repositories.datastore

import com.glob.mytrips.data.model.PhotoData
import com.glob.mytrips.domain.dtos.PhotoDto
import io.reactivex.Completable
import io.reactivex.Single

interface PhotoDataStore {
    fun getPhotos(idPlace: Int): Single<List<PhotoData>>
    fun savePhotos(photos: List<PhotoDto>): Completable
    fun isCached(idPlace: Int): Single<Boolean>
}