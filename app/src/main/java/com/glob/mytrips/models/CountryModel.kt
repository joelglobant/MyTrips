package com.glob.mytrips.models

data class CountryModel(
    val id: Int,
    val name: String,
    val states: List<StateModel>
)