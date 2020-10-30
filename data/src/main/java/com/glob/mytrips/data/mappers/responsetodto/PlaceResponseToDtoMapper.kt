package com.glob.mytrips.data.mappers.responsetodto

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.remote.response.PlaceResponse
import com.glob.mytrips.domain.dtos.PlaceDto

class PlaceResponseToDtoMapper : Mapper<PlaceResponse, PlaceDto> {
    override fun transform(value: PlaceResponse): PlaceDto {
        return with(value) {
            PlaceDto(
                id, idState, name, description, rank, favorite
            )
        }
    }
}