package com.glob.mytrips.domain.providers

import com.glob.mytrips.domain.dtos.PhotoDto
import com.glob.mytrips.domain.usecases.GetPhotosUseCase
import com.glob.mytrips.domain.usecases.SingleUseCase

interface PhotoProvider {
    fun getPhotosByPlaceUseCase(idPlace: Int): SingleUseCase<GetPhotosUseCase.Params, List<PhotoDto>>
}