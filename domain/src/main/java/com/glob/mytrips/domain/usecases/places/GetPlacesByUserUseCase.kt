package com.glob.mytrips.domain.usecases.places

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.PlacesRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import io.reactivex.Single

class GetPlacesByUserUseCase(
    private val placesRepository: PlacesRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetPlacesByUserUseCase.Params, List<PlaceDto>>(threadExecutor, postExecutorThread) {

    data class Params(val idUser: Int)

    override fun buildSingleUseCase(params: Params?): Single<List<PlaceDto>> {
        return params?.let {
            placesRepository.getPlacesByUser(it.idUser)
        } ?: Single.error(Throwable("Invalid Arguments"))
    }

}