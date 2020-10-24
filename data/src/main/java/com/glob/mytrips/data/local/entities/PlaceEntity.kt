package com.glob.mytrips.data.local.entities

data class PlaceEntity (
    val id: Int,
    val name: String,
    val photos: List<PhotoEntity>,
    val description: String,
    val rate: Double?,
    val favorite: Boolean
)