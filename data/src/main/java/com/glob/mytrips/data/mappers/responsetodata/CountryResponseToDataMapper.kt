package com.glob.mytrips.data.mappers.responsetodata

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.CountryData
import com.glob.mytrips.data.remote.response.CountryResponse

class CountryResponseToDataMapper(private val stateMapper: StateResponseToDataMapper) :
    Mapper<CountryResponse, CountryData> {
    override fun transform(value: CountryResponse): CountryData {
        return with(value) {
            CountryData(id, idUser, name, states.map { stateMapper.transform(it) })
        }
    }
}