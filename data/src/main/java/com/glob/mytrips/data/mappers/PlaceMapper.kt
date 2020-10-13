package com.glob.mytrips.data.mappers

import com.glob.mytrips.data.remote.request.PlaceRequest
import com.glob.mytrips.data.remote.response.PlaceResponse
import com.glob.mytrips.domain.dtos.PhotoDto
import com.glob.mytrips.domain.dtos.PlaceDto

class PlaceMapper : MapperManager<PlaceResponse, PlaceDto, PlaceRequest>() {
    operator fun invoke(placeResponse: PlaceResponse): PlaceDto {
        return with(placeResponse) {
            PlaceDto(id, name, photos.map { PhotoDto(it.url) } , description, rank, favorite)
        }
    }

    override fun mapFromService(type: PlaceResponse): PlaceDto {
        return with(type) {
            PlaceDto(id, name, photos.map { PhotoDto(it.url) }, description, rank, favorite)
        }
    }

    override fun mapToRequest(type: PlaceDto): PlaceRequest {
        return with(type) {
            PlaceRequest(id, name, description)
        }
    }

}