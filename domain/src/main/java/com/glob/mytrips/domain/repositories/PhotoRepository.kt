package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.PhotoDto
import io.reactivex.Completable
import io.reactivex.Single

interface PhotoRepository {
    fun getPhotos(idPlace: Int): Single<List<PhotoDto>>
    fun savePhotos(photos: List<PhotoDto>): Completable
}