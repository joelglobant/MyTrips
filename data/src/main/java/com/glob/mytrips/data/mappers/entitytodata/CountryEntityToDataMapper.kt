package com.glob.mytrips.data.mappers.entitytodata

import com.glob.mytrips.data.local.entities.CountryEntity
import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.CountryData

class CountryEntityToDataMapper : Mapper<CountryEntity, CountryData> {
    override fun transform(value: CountryEntity): CountryData {
        return with(value) {
            CountryData(id, idUser, name, null)
        }
    }

    override fun reverseTransform(value: CountryData): CountryEntity {
        return with(value) {
            CountryEntity(id, idUser, name)
        }
    }
}