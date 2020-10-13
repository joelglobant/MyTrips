package com.glob.mytrips.domain.dtos

import com.glob.mytrips.domain.dtos.base.PlaceReference
import java.io.Serializable

data class CountryDto(
    val id: Int,
    val name: String,
    val states: List<StateDto>
    //val location: LatLon
): Serializable, PlaceReference {
    override fun name() = name
}