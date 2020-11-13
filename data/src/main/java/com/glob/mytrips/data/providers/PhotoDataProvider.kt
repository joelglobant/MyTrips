package com.glob.mytrips.data.providers

import com.glob.mytrips.domain.dtos.PhotoDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.PhotoRepository
import com.glob.mytrips.domain.usecases.GetPhotosUseCase
import com.glob.mytrips.domain.usecases.SingleUseCase

class PhotoDataProvider(
    private val photoRepository: PhotoRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutorThread: PostExecutorThread
) {
    fun getPhotosByPlaceUseCase(): SingleUseCase<GetPhotosUseCase.Params, List<PhotoDto>> {
        return GetPhotosUseCase(photoRepository, threadExecutor, postExecutorThread)
    }
}