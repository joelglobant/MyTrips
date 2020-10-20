package com.glob.mytrips.domain.dtos

data class UserDto (
    val id: Int,
    val name: String,
    val nickname: String,
    val surname: String,
    val bio: String,
    val generalPlaces: List<CountryDto>
)