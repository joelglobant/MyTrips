package com.glob.mytrips.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("idUser")
    val id: Int,
    @SerializedName("nameUser")
    val name: String,
    @SerializedName("nicknameUser")
    val nickname: String,
    @SerializedName("surnameUser")
    val surname: String,
    @SerializedName("bioUser")
    val bio: String,
    @SerializedName("countriesUser")
    val countries: List<CountryResponse>
)