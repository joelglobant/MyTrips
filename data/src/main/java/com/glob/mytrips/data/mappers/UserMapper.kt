package com.glob.mytrips.data.mappers

import com.glob.mytrips.data.remote.response.UserResponse
import com.glob.mytrips.domain.dtos.UserDto

class UserMapper(private val countryMapper: CountryMapper) : Mapper<UserResponse, UserDto> {
    override fun transform(value: UserResponse): UserDto {
        return with(value) {
            UserDto(id, name, nickname, surname, bio, countries.map { countryMapper.transform(it) })
        }
    }
}