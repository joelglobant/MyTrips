package com.glob.mytrips.data.remote.response

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("photo")
    val url: String
)