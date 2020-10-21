package com.glob.mytrips.data.mappers

import com.glob.mytrips.data.remote.response.UserResponse
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.UserDto

class UserMapper {
    operator fun invoke(userResponse: UserResponse): UserDto {
        return with(userResponse) {
            val countryList = arrayListOf<CountryDto>()
            countries.forEach { country ->
                countryList.add(CountryMapper().invoke(country) )
            }
            UserDto(id, name, nickname, surname, bio, countryList)
        }
    }
}