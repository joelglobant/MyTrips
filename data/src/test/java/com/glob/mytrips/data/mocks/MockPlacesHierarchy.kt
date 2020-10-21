package com.glob.mytrips.data.mocks

import com.glob.mytrips.data.remote.response.CountryResponse
import com.glob.mytrips.data.remote.response.PlaceResponse
import com.glob.mytrips.data.remote.response.StateResponse
import com.glob.mytrips.data.remote.response.UserResponse

class MockPlacesHierarchy {
    companion object {
        val idPlace: Int
            get() = 1

        val placeResponse: PlaceResponse
            get() = PlaceResponse(idPlace, "Jerez", emptyList(), "amazing place", null, false)

        val idState: Int
            get() = 1

        val state: StateResponse
            get() = StateResponse(idState, "Zacatecas", listOf(placeResponse))

        val idCountry: Int
            get() = 1

        val country: CountryResponse
            get() = CountryResponse(idCountry, "Mexico", listOf(state))

        val idUser: Int
            get() = 1

        val userResponse: UserResponse
            get() = UserResponse(
                idUser,
                "joel",
                "Leoboyean",
                "Aparicio P",
                "traveler",
                listOf(country)
            )
    }
}