package com.glob.mytrips.models

//@Parcelize
data class UserModel(
    val id: Int,
    val name: String,
    val nickname: String,
    val surname: String,
    val bio: String,
    val generalPlaces: List<CountryModel>
)// : Parcelable