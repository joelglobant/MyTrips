package com.glob.mytrips.data.mappers

import com.glob.mytrips.data.remote.response.StateResponse
import com.glob.mytrips.domain.dtos.StateDto

/**
 * this mapper need to be in cascade because
 * @placeMapper
 *
 */
class StateMapper(private val placeMapper: PlaceMapper) : Mapper<StateResponse, StateDto> {
    override fun transform(value: StateResponse): StateDto {
        return with(value) {
            StateDto(id, idCountry, name, places.map { placeMapper.transform(it) })
        }
    }
}