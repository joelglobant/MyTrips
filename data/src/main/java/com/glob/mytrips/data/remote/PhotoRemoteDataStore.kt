package com.glob.mytrips.data.remote

import com.glob.mytrips.data.mappers.responsetodata.PhotoResponseToDataMapper
import com.glob.mytrips.data.model.PhotoData
import com.glob.mytrips.data.repositories.datastore.PhotoDataStore
import com.glob.mytrips.data.repositories.datastore.remote.PhotoRemote
import com.glob.mytrips.domain.dtos.PhotoDto
import io.reactivex.Completable
import io.reactivex.Single

class PhotoRemoteDataStore(
    private val photoRemote: PhotoRemote,
    private val photoRespToData: PhotoResponseToDataMapper
) : PhotoDataStore {
    override fun getPhotos(idPlace: Int): Single<List<PhotoData>> {
        return photoRemote.getPhotos(idPlace)
            .flatMap { photosResp ->
                return@flatMap Single.just(photosResp.map { photoRespToData.transform(it) })
            }
    }

    override fun savePhotos(photos: List<PhotoDto>): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(idPlace: Int): Single<Boolean> {
        throw UnsupportedOperationException()
    }

}