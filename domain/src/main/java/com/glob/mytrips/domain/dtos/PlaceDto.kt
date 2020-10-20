package com.glob.mytrips.domain.dtos

data class PlaceDto(
    val id: Int,
    val name: String,
    val photos: List<PhotoDto>,
    val description: String,
    val rank: Double?,
    val favorite: Boolean
)