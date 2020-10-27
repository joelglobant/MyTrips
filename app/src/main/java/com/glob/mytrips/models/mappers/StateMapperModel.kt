package com.glob.mytrips.models.mappers

import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.models.StateModel

class StateMapperModel(private val placeMapperModel: PlaceMapperModel): Transform<StateDto, StateModel>() {
    override fun transform(value: StateDto): StateModel {
        return with(value) {
            StateModel(id, idCountry, name, places.map { placeMapperModel.transform(it) })
        }
    }

    override fun reverseTransform(value: StateModel): StateDto {
        return with(value) {
            val placesDto = arrayListOf<PlaceDto>()
            places.forEach { place ->
                placesDto.add(placeMapperModel.reverseTransform(place))
            }
            StateDto(id, idCountry, name, placesDto)
        }
    }
}