package com.glob.mytrips.data.mappers.responsetodata

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.PlaceData
import com.glob.mytrips.data.remote.response.PlaceResponse

class PlaceResponseToDataMapper(private val photoMapper: PhotoResponseToDataMapper) :
    Mapper<PlaceResponse, PlaceData> {
    override fun transform(value: PlaceResponse): PlaceData {
        return with(value) {
            PlaceData(
                id, idState, name, description, rank, favorite,
                photos.map { photoMapper.transform(it) }
            )
        }
    }
}