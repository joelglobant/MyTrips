package com.glob.mytrips.domain.dtos

data class UserDto (
    val id: Int,
    val name: String,
    val nickName: String,
    val surname: String,
    val bio: String,
    val generalPlaces: List<CountryDto>
)