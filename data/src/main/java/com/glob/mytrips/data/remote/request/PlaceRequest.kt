package com.glob.mytrips.data.remote.request

data class PlaceRequest(
    val id: Int,
    val name: String,
    val description: String

/*
val photos: List<String>,
    val description: String,
    val rank: Double, <<--- var
    val favorite: Boolean
 */

)