package com.glob.mytrips.providers

import com.glob.mytrips.domain.dtos.PhotoDto
import com.glob.mytrips.domain.usecases.GetPhotosUseCase
import com.glob.mytrips.domain.usecases.SingleUseCase

interface PhotoProvider {
    fun getPhotosByPlaceUseCase(): SingleUseCase<GetPhotosUseCase.Params, List<PhotoDto>>
}