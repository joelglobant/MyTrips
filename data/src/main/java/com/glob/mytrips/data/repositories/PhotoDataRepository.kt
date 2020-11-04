package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.PhotoDataStoreFactory
import com.glob.mytrips.data.mappers.datatodto.PhotoDataToDtoMapper
import com.glob.mytrips.data.mappers.responsetodto.UserResponseToDtoMapper
import com.glob.mytrips.data.remote.services.UserServices
import com.glob.mytrips.domain.dtos.PhotoDto
import com.glob.mytrips.domain.repositories.PhotoRepository
import io.reactivex.Completable
import io.reactivex.Single

class PhotoDataRepository(
    private val factory: PhotoDataStoreFactory,
    private val photoDataToDto: PhotoDataToDtoMapper
) : PhotoRepository {
    override fun getPhotos(idPlace: Int): Single<List<PhotoDto>> {
        return factory.retrieveLocalDataSource().isCached(idPlace)
            .flatMap {
                factory.retrieveDataSource(it).getPhotos(idPlace)
            }.flatMap { photosData ->
                val photosDto = photosData.map {
                    photoDataToDto.transform(it)
                }

                return@flatMap savePhotos(photosDto)
                    .toSingle { photosDto }
            }
    }

    override fun savePhotos(photos: List<PhotoDto>): Completable {
        return factory.retrieveLocalDataSource().savePhotos(photos)
    }
}