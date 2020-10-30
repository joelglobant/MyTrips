package com.glob.mytrips.data.model


data class UserData (
    val id: Int,
    val name: String,
    val nickname: String,
    val surname: String,
    val bio: String,
    val countries: List<CountryData>?
)