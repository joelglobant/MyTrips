package com.glob.mytrips.domain.dtos

import com.glob.mytrips.domain.dtos.base.PlaceReference
import java.io.Serializable


data class PlaceDto(
    val id: Int,
    val name: String,
    val photos: List<PhotoDto>,
    val description: String,
    val rank: Double?,
    val favorite: Boolean
): PlaceReference {
    override fun name() = name
}