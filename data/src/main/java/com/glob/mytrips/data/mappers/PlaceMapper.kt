package com.glob.mytrips.data.mappers

import com.glob.mytrips.data.remote.response.PlaceResponse
import com.glob.mytrips.domain.dtos.PlaceDto

/**
 * this mapper need to be in cascade because
 * @mapperPhoto
 */
class PlaceMapper(private val photoMapper: PhotoMapper) : Mapper<PlaceResponse, PlaceDto> {

    override fun transform(value: PlaceResponse): PlaceDto {
        return with(value) {
            PlaceDto(
                id, idState, name, photos.map { photoMapper.transform(it) },
                description, rank, favorite
            )
        }
    }
}