package com.glob.mytrips.models.mappers

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.models.PlaceModel

class PlaceMapperModel(private val photoMapperModel: PhotoMapperModel) :
    Transform<PlaceDto, PlaceModel>() {
    override fun transform(value: PlaceDto): PlaceModel {
        return with(value) {
            PlaceModel(
                id,
                idState,
                name,
                photos.map { photoMapperModel.transform(it) },
                description,
                rank,
                favorite
            )
        }
    }

    override fun reverseTransform(value: PlaceModel): PlaceDto {
        return with(value) {
            PlaceDto(
                id,
                idState,
                name,
                photos.map { photoMapperModel.reverseTransform(it) },
                description,
                rank,
                favorite
            )
        }
    }
}