package com.glob.mytrips.data.mappers

import com.glob.mytrips.data.remote.response.CountryResponse
import com.glob.mytrips.domain.dtos.CountryDto

/**
 * this mapper need to be in cascade because
 * @mapperState
 *
  */
class CountryMapper(private val mapperState: StateMapper) : Mapper<CountryResponse, CountryDto> {
    override fun transform(value: CountryResponse): CountryDto {
        return with(value) {
            CountryDto(id, name, states.map { mapperState.transform(it) })
        }
    }
}