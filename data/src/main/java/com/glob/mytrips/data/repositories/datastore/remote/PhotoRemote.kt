package com.glob.mytrips.data.repositories.datastore.remote

import com.glob.mytrips.data.remote.response.PhotoResponse
import io.reactivex.Single

interface PhotoRemote {
    fun getPhotos(idPlace: Int): Single<List<PhotoResponse>>
}