package com.glob.mytrips.data.providers

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.providers.PlaceProvider
import com.glob.mytrips.domain.repositories.PlaceRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.places.GetPlaceByIdUseCase
import com.glob.mytrips.domain.usecases.places.GetPlacesByStateUseCase

class PlaceDataProvider(
    private val placeRepository: PlaceRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutorThread: PostExecutorThread
) : PlaceProvider {

    override fun getPlaceByIdUseCase(): SingleUseCase<GetPlaceByIdUseCase.Params, PlaceDto> {
        return GetPlaceByIdUseCase(placeRepository, threadExecutor, postExecutorThread)
    }

    override fun getPlacesUseCase(): SingleUseCase<GetPlacesByStateUseCase.Params, List<PlaceDto>> {
        return GetPlacesByStateUseCase(placeRepository, threadExecutor, postExecutorThread)
    }
}