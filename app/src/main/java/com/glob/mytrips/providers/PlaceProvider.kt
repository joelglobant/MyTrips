package com.glob.mytrips.providers

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.places.GetPlaceByIdUseCase
import com.glob.mytrips.domain.usecases.places.GetPlacesByStateUseCase

interface PlaceProvider {
    fun getPlaceByIdUseCase(): SingleUseCase<GetPlaceByIdUseCase.Params, PlaceDto>
    fun getPlacesUseCase(): SingleUseCase<GetPlacesByStateUseCase.Params, List<PlaceDto>>
}