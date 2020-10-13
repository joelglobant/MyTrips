package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.mappers.PlaceMapper
import com.glob.mytrips.data.remote.services.PlaceServices
import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.repositories.PlacesRepository
import io.reactivex.Single

class PlaceDataRepository(private val placeServices: PlaceServices) : PlacesRepository {

    override fun getPlaceById(idPlace: Int): Single<PlaceDto> {
        return placeServices.getPlaceDetail(idPlace)
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let {
                        Single.just(
                            PlaceMapper().invoke(it)
                        )
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }

    override fun getPlacesByUser(idUser: Int): Single<List<PlaceDto>> {
        return placeServices.getPlacesByUser(idUser)
            .flatMap {response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let {places ->
                        val listPlaces = arrayListOf<PlaceDto>()
                        places.forEach {
                            listPlaces.add(
                                PlaceMapper().invoke(it)
                            )
                        }
                        Single.just(listPlaces)
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }

}