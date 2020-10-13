package com.glob.mytrips.data.remote.response

import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("idCountry")
    val id: Int,
    @SerializedName("nameCountry")
    val name: String,
    @SerializedName("statesCountry")
    val states: List<StateResponse>
)