package com.glob.mytrips.data.providers

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.providers.PlaceProvider
import com.glob.mytrips.domain.repositories.PlacesRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.places.GetPlaceByIdUseCase
import com.glob.mytrips.domain.usecases.places.GetPlacesByUserUseCase

class PlaceDataProvider(
    private val placesRepository: PlacesRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutorThread: PostExecutorThread
) : PlaceProvider {

    override fun getPlaceByIdUseCase(): SingleUseCase<GetPlaceByIdUseCase.Params, PlaceDto> {
        return GetPlaceByIdUseCase(placesRepository, threadExecutor, postExecutorThread)
    }

    override fun getPlacesUseCase(): SingleUseCase<GetPlacesByUserUseCase.Params, List<PlaceDto>> {
        return GetPlacesByUserUseCase(placesRepository, threadExecutor, postExecutorThread)
    }
}