package com.glob.mytrips.data.model

data class CountryData (
    val id: Int,
    val idUser: Int,
    val name: String,
    val states: List<StateData>?
)