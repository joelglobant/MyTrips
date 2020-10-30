package com.glob.mytrips.domain.usecases

import com.glob.mytrips.domain.dtos.PhotoDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.PhotoRepository
import io.reactivex.Single

class GetPhotosUseCase(
    private val photoRepository: PhotoRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetPhotosUseCase.Params, List<PhotoDto>> (
    threadExecutor,
    postExecutorThread
) {
    data class Params(val idPlace: Int)

    override fun buildSingleUseCase(params: Params?): Single<List<PhotoDto>> {
        return params?.let {
            photoRepository.getPhotos(it.idPlace)
        } ?: Single.error(Throwable("Photos not found"))
    }
}