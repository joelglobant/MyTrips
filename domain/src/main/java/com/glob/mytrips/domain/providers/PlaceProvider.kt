package com.glob.mytrips.domain.providers

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.places.GetPlaceByIdUseCase
import com.glob.mytrips.domain.usecases.places.GetPlacesByUserUseCase

interface PlaceProvider {
    fun getPlaceByIdUseCase(): SingleUseCase<GetPlaceByIdUseCase.Params, PlaceDto>
    fun getPlacesUseCase(): SingleUseCase<GetPlacesByUserUseCase.Params, List<PlaceDto>>
}