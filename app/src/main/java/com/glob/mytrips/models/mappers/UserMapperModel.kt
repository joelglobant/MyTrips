package com.glob.mytrips.models.mappers

import com.glob.mytrips.domain.dtos.UserDto
import com.glob.mytrips.models.UserModel

class UserMapperModel : Transform<UserDto, UserModel>() {
    override fun transform(value: UserDto): UserModel {
        return with(value) {
            UserModel(id, name, nickname, surname, bio)
        }
    }
}