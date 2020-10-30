package com.glob.mytrips.data.mappers.entitytodata

import com.glob.mytrips.data.local.entities.PlaceEntity
import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.PlaceData

class PlaceEntityToDataMapper : Mapper<PlaceEntity, PlaceData> {
    override fun transform(value: PlaceEntity): PlaceData {
        return with(value) {
            PlaceData(id, idState, name, description, rate, favorite, null)
        }
    }
}