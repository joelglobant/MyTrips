package com.glob.mytrips.data.mappers.responsetodto

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.remote.response.CountryResponse
import com.glob.mytrips.domain.dtos.CountryDto

class CountryResponseToDtoMapper : Mapper<CountryResponse, CountryDto> {
    override fun transform(value: CountryResponse): CountryDto {
        return with(value) {
            CountryDto(id, idUser, name)
        }
    }
}