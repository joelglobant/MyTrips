package com.glob.mytrips.domain.dtos

data class CountryDto(
    val id: Int,
    val name: String,
    val states: List<StateDto>
    //val location: LatLon
)