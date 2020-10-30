package com.glob.mytrips.data.remote

import com.glob.mytrips.data.remote.response.PhotoResponse
import com.glob.mytrips.data.remote.services.PhotoServices
import com.glob.mytrips.data.repositories.datastore.remote.PhotoRemote
import io.reactivex.Single

class PhotoRemoteImpl(
    private val photoServices: PhotoServices
): PhotoRemote {

    override fun getPhotos(idPlace: Int): Single<List<PhotoResponse>> {
        // TODO: 28/10/2020 made a random selection
        return photoServices.getPhotosByPlace(idPlace)
            .flatMap { response ->
                return@flatMap if (response.isSuccessful) {
                    response.body()?.let { photoResponse ->
                        Single.just(photoResponse)
                    }
                } else {
                    Single.error(Throwable(response.errorBody().toString()))
                }
            }
    }
}