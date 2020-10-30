package com.glob.mytrips.models.mappers

import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.models.StateModel

class StateMapperModel: Transform<StateDto, StateModel>() {
    override fun transform(value: StateDto): StateModel {
        return with(value) {
            StateModel(id, name)
        }
    }

}