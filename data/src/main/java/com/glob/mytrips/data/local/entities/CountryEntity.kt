package com.glob.mytrips.data.local.entities

data class CountryEntity (
    val id: Int,
    val name: String,
    val states: List<StateEntity>
)