package com.glob.mytrips.domain.usecases.places

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.PlaceRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import io.reactivex.Single

class GetPlaceByIdUseCase(
    private val placeRepository: PlaceRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetPlaceByIdUseCase.Params, PlaceDto> (threadExecutor, postExecutorThread){

    data class Params(val idPlace: Int)

    override fun buildSingleUseCase(params: Params?): Single<PlaceDto> {
        return params?.let {
            placeRepository.getPlace(it.idPlace)
        } ?: Single.error(Throwable("Place not found"))
    }
}