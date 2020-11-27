package com.glob.mytrips.data.mocks

import com.glob.mytrips.data.remote.response.*

object MockPlacesHierarchy {
    val photosResp: PhotoResponse
        get() = PhotoResponse(1, 1, "Test.con/imgTest")

    val placeResp: PlaceResponse
        get() = PlaceResponse(
            1, 1, "Jerez", "amazing place",
            null, false, listOf(photosResp)
        )

    val stateResp: StateResponse
        get() = StateResponse(1, 1, "Zacatecas", listOf(placeResp))

    val countryResp: CountryResponse
        get() = CountryResponse(1, 1, "Mexico", listOf(stateResp))

    val userResponse: UserResponse
        get() = UserResponse(
            1,
            "joel",
            "Leoboyean",
            "Aparicio P",
            "traveler",
            listOf(countryResp)
        )
}