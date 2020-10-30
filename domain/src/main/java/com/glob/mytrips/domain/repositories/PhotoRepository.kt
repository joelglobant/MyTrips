package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.PhotoDto
import io.reactivex.Single

interface PhotoRepository {
    fun getPhotos(idUser: Int): Single<List<PhotoDto>>
}