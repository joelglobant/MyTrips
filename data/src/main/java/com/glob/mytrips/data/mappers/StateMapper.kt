package com.glob.mytrips.data.mappers

import com.glob.mytrips.data.remote.response.StateResponse
import com.glob.mytrips.domain.dtos.PlaceDto
import com.glob.mytrips.domain.dtos.StateDto

class StateMapper {
    operator fun invoke(stateResponse: StateResponse): StateDto {
        return with(stateResponse) {
            val placesDto = arrayListOf<PlaceDto>()
            places.forEach { place ->
                placesDto.add(PlaceMapper().invoke(place))
            }
            StateDto(id, name, placesDto)
        }
    }
}