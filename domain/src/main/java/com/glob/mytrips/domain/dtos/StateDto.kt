package com.glob.mytrips.domain.dtos

data class StateDto (
    val id: Int,
    val name: String,
    val places: List<PlaceDto>
)