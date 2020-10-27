package com.glob.mytrips.data.remote.response

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("idPhoto")
    val id: Int,
    @SerializedName("idPlace")
    val idPlace: Int,
    @SerializedName("photo")
    val url: String
)