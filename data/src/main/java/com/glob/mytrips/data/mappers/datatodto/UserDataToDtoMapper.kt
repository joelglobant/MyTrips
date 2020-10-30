package com.glob.mytrips.data.mappers.datatodto

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.UserData
import com.glob.mytrips.domain.dtos.UserDto

class UserDataToDtoMapper : Mapper<UserData, UserDto> {
    override fun transform(value: UserData): UserDto {
        return with(value) {
            UserDto(id, name, nickname, surname, bio)
        }
    }

    override fun reverseTransform(value: UserDto): UserData {
        return with(value){
            UserData(id, name, nickname, surname, bio, null)
        }
    }
}