package com.glob.mytrips.data.mappers.entitytodata

import com.glob.mytrips.data.local.entities.StateEntity
import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.StateData

class StateEntityToDataMapper: Mapper<StateEntity, StateData> {
    override fun transform(value: StateEntity): StateData {
        return with(value){
            StateData(id, idCountry, name, null)
        }
    }
}