package com.glob.mytrips.data.local

import com.glob.mytrips.data.mappers.datatodto.PhotoDataToDtoMapper
import com.glob.mytrips.data.mappers.entitytodata.PhotoEntityToDataMapper
import com.glob.mytrips.data.model.PhotoData
import com.glob.mytrips.data.repositories.datastore.PhotoDataStore
import com.glob.mytrips.data.repositories.datastore.cache.PhotoCache
import com.glob.mytrips.domain.dtos.PhotoDto
import io.reactivex.Completable
import io.reactivex.Single

class PhotoCacheDataStore(
    private val photoCache: PhotoCache,
    private val photoEntityToData: PhotoEntityToDataMapper,
    private val photoDataToDto: PhotoDataToDtoMapper
) : PhotoDataStore {
    override fun getPhotos(idPlace: Int): Single<List<PhotoData>> {
        return photoCache.getPhotos(idPlace)
            .map { photos ->
                photos.map { photoEntityToData.transform(it) }
            }
    }

    override fun savePhotos(photos: List<PhotoDto>): Completable {
        val photoData = photos.map { photoDataToDto.reverseTransform(it) }
        return photoCache.savePhotos(photoData.map { photoEntityToData.reverseTransform(it) }).doOnComplete {
            println("Photos Saved!!! ---")
        }
    }

    override fun isCached(idPlace: Int): Single<Boolean> {
        return photoCache.isCached(idPlace)
    }
}