package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.PlaceDto
import io.reactivex.Single

interface PlacesRepository {
    fun getPlaceById(idPlace: Int): Single<PlaceDto>
    fun getPlacesByUser(idUser: Int): Single<List<PlaceDto>>
}