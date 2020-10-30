package com.glob.mytrips.data.model

data class PlaceData(
    val id: Int,
    val idState: Int,
    val name: String,
    val description: String,
    val rank: Double?,
    val favorite: Boolean,
    val photos: List<PhotoData>?
)