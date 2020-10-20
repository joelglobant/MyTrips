package com.glob.mytrips.models.mappers

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.models.PlaceModel

class PlaceMapperModel: Transform<PlaceDto, PlaceModel>() {
    override fun transform(value: PlaceDto): PlaceModel {
        return with(value) {
            PlaceModel(
                id,
                name,
                photos.map { com.glob.mytrips.models.PhotoModel(it.url) },
                description,
                rank,
                favorite)
        }
    }
}