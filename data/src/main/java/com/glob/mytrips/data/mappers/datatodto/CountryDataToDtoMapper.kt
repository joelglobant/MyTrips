package com.glob.mytrips.data.mappers.datatodto

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.CountryData
import com.glob.mytrips.domain.dtos.CountryDto

class CountryDataToDtoMapper : Mapper<CountryData, CountryDto> {
    override fun transform(value: CountryData): CountryDto {
        return with(value) {
            CountryDto(id, idUser, name)
        }
    }

    override fun reverseTransform(value: CountryDto): CountryData {
        return with(value) {
            CountryData(id, idUser, name, null)
        }
    }
}