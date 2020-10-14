package com.glob.mytrips.domain.dtos

import com.glob.mytrips.domain.dtos.base.PlaceReference
import java.io.Serializable

data class StateDto (
    val id: Int,
    val name: String,
    val places: List<PlaceDto>
): PlaceReference {
    override fun name() = name
}