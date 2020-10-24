package com.glob.mytrips.data.local.entities

data class StateEntity (
    val id: Int,
    val name: String,
    val places: List<PlaceEntity>
)