package com.glob.mytrips.data.mappers

import com.glob.mytrips.data.remote.response.CountryResponse
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.StateDto

class CountryMapper {
    operator fun invoke(countryResponse: CountryResponse): CountryDto {
        return with(countryResponse) {
            val stateList = arrayListOf<StateDto>()
            states.forEach { state ->
                stateList.add(
                    StateMapper().invoke(state)
                )
            }
            CountryDto(id, name, stateList)
        }
    }
}