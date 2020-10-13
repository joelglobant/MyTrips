package com.glob.mytrips.domain.usecases.places

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.PlacesRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import io.reactivex.Single

class GetPlaceByIdUseCase(
    private val placesRepository: PlacesRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetPlaceByIdUseCase.Params, PlaceDto> (threadExecutor, postExecutorThread){

    data class Params(val id: Int)

    override fun buildSingleUseCase(params: Params?): Single<PlaceDto> {
        return params?.let {
            placesRepository.getPlaceById(it.id)
        } ?: Single.error(Throwable("Place not found"))
    }

}