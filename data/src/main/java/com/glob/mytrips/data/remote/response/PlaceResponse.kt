package com.glob.mytrips.data.remote.response

import com.google.gson.annotations.SerializedName

data class PlaceResponse(
    @SerializedName("idPlace")
    val id: Int,
    @SerializedName("idState")
    val idState: Int,
    @SerializedName("namePlace")
    val name: String,
    @SerializedName("descPlace")
    val description: String,
    @SerializedName("ranckPlace")
    val rank: Double?,
    @SerializedName("favoritePlace")
    val favorite: Boolean,
    @SerializedName("photosPlaces")
    val photos: List<PhotoResponse>
)