package com.glob.mytrips.data.mappers.entitytodata

import com.glob.mytrips.data.local.entities.UserEntity
import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.UserData

class UserEntityToDataMapper :
    Mapper<UserEntity, UserData> {
    override fun transform(value: UserEntity): UserData {
        return with(value) {
            UserData(
                id,
                name ?: "",
                nickname ?: "",
                surname ?: "",
                bio ?: "",
                null
            )
        }
    }

    override fun reverseTransform(value: UserData): UserEntity {
        return with(value) {
            UserEntity(id, name, nickname, surname, bio)
        }
    }
}