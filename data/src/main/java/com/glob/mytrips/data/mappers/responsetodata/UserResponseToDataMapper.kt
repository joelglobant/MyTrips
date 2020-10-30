package com.glob.mytrips.data.mappers.responsetodata

import com.glob.mytrips.data.mappers.Mapper
import com.glob.mytrips.data.model.UserData
import com.glob.mytrips.data.remote.response.UserResponse

class UserResponseToDataMapper(
    private val countryMapper: CountryResponseToDataMapper
) : Mapper<UserResponse, UserData> {
    override fun transform(value: UserResponse): UserData {
        return with(value) {
            UserData(
                id, name, nickname, surname, bio,
                countries.map { countryMapper.transform(it) })
        }
    }
}