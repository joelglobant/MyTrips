package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.PlaceDataStoreFactory
import com.glob.mytrips.data.mappers.datatodto.PhotoDataToDtoMapper
import com.glob.mytrips.data.mappers.datatodto.PlaceDataToDtoMapper
import com.glob.mytrips.data.model.PhotoData
import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.repositories.PhotoRepository
import com.glob.mytrips.domain.repositories.PlaceRepository
import io.reactivex.Completable
import io.reactivex.Single

class PlaceDataRepository(
    private val factory: PlaceDataStoreFactory,
    private val photoRepository: PhotoRepository,
    private val placeDataToDto: PlaceDataToDtoMapper,
    private val photoDataToDto: PhotoDataToDtoMapper
) : PlaceRepository {
    override fun getPlaces(idPlace: Int): Single<List<PlaceDto>> {
        return factory.retrieveLocalDataSource().isCached(idPlace)
            .flatMap {
                factory.retrieveDataSource(it).getPlaces(idPlace)
            }.flatMap { placesData ->
                placesData.forEach { place ->
                    savePhotos(place.photos)
                }
                return@flatMap savePlaces(placesData.map { placeDataToDto.transform(it) })
                    .toSingle {
                        placesData.map { placeDataToDto.transform(it) }
                    }
            }
    }

    override fun getPlace(idPlace: Int): Single<PlaceDto> {
        return factory.retrieveLocalDataSource().isCached(idPlace)
            .flatMap {
                factory.retrieveDataSource(it).getPlace(idPlace)
            }.flatMap {
                return@flatMap Single.just(placeDataToDto.transform(it))
            }
    }

    override fun savePlaces(places: List<PlaceDto>): Completable {
        return factory.retrieveLocalDataSource().savePlaces(places)
    }

    private fun savePhotos(photos: List<PhotoData>?) {
        photos?.let {
            photoRepository.savePhotos(photos.map { photoDataToDto.transform(it) })
        }
    }
}