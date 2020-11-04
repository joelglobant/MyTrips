package com.glob.mytrips.data.mappers.datatodto

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.PlaceData
import com.glob.mytrips.domain.dtos.PlaceDto

class PlaceDataToDtoMapper: Mapper<PlaceData, PlaceDto> {
    override fun transform(value: PlaceData): PlaceDto {
        return with(value) {
            PlaceDto(id, idState, name, description, rank, favorite)
        }
    }

    override fun reverseTransform(value: PlaceDto): PlaceData {
        return with(value) {
            PlaceData(id, idState, name, description, rank, favorite, null)
        }
    }
}