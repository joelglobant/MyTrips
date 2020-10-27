package com.glob.mytrips.models

data class PlaceModel(
    val id: Int,
    val idState: Int,
    val name: String,
    val photos: List<PhotoModel>,
    val description: String,
    val rank: Double?,
    val favorite: Boolean
)