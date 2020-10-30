package com.glob.mytrips.data.mappers.responsetodto

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.remote.response.StateResponse
import com.glob.mytrips.domain.dtos.StateDto

class StateResponseToDtoMapper : Mapper<StateResponse, StateDto> {
    override fun transform(value: StateResponse): StateDto {
        return with(value) {
            StateDto(id, id, name)
        }
    }
}