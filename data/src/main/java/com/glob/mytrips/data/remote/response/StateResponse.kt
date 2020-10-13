package com.glob.mytrips.data.remote.response

import com.google.gson.annotations.SerializedName

data class StateResponse(
    @SerializedName("idState")
    val id: Int,
    @SerializedName("nameState")
    val name: String,
    @SerializedName("placesState")
    val places: List<PlaceResponse>
)