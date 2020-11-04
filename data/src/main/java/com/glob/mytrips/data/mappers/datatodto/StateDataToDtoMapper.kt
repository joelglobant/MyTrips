package com.glob.mytrips.data.mappers.datatodto

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.StateData
import com.glob.mytrips.domain.dtos.StateDto

class StateDataToDtoMapper: Mapper<StateData, StateDto> {
    override fun transform(value: StateData): StateDto {
        return with (value) {
            StateDto(id, idCountry, name)
        }
    }

    override fun reverseTransform(value: StateDto): StateData {
        return with(value) {
            StateData(id, idCountry, name, null)
        }
    }
}