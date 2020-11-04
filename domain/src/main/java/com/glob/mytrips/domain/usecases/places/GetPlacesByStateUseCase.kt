package com.glob.mytrips.domain.usecases.places

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.PlaceRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import io.reactivex.Single

class GetPlacesByStateUseCase(
    private val placeRepository: PlaceRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetPlacesByStateUseCase.Params, List<PlaceDto>>(threadExecutor, postExecutorThread) {

    data class Params(val idState: Int)

    override fun buildSingleUseCase(params: Params?): Single<List<PlaceDto>> {
        return params?.let {
            placeRepository.getPlaces(it.idState)
        } ?: Single.error(Throwable("Invalid Arguments"))
    }

}