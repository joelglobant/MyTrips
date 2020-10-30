package com.glob.mytrips.data.mappers.responsetodto

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.remote.response.UserResponse
import com.glob.mytrips.domain.dtos.UserDto

class UserResponseToDtoMapper : Mapper<UserResponse, UserDto> {
    override fun transform(value: UserResponse): UserDto {
        return with(value) {
            UserDto(id, name, nickname, surname, bio)
        }
    }
}