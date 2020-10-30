package com.glob.mytrips.data.model


data class StateData (
    val id: Int,
    val idCountry: Int,
    val name: String,
    val places: List<PlaceData>?
)