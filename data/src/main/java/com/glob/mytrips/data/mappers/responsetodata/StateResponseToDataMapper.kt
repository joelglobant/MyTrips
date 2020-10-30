package com.glob.mytrips.data.mappers.responsetodata

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.StateData
import com.glob.mytrips.data.remote.response.StateResponse


class StateResponseToDataMapper(
    private val placeMapper: PlaceResponseToDataMapper
) : Mapper<StateResponse, StateData> {
    override fun transform(value: StateResponse): StateData {
        return with(value) {
            StateData(id, idCountry, name, places.map { placeMapper.transform(it) })
        }
    }
}