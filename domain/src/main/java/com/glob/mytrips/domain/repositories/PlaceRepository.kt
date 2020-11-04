package com.glob.mytrips.domain.repositories

import com.glob.mytrips.domain.dtos.PlaceDto
import io.reactivex.Completable
import io.reactivex.Single

interface PlaceRepository {
    fun getPlaces(idPlace: Int): Single<List<PlaceDto>>
    fun getPlace(idPlace: Int): Single<PlaceDto>
    fun savePlaces(places: List<PlaceDto>): Completable
}