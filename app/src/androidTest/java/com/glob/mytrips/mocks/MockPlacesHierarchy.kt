package com.glob.mytrips.data.mocks

import com.glob.mytrips.data.remote.response.CountryResponse
import com.glob.mytrips.data.remote.response.PlaceResponse
import com.glob.mytrips.data.remote.response.StateResponse
import com.glob.mytrips.data.remote.response.UserResponse

class MockPlacesHierarchy {
    companion object {
        val idPlace: Int
            get() = 1

        private val placeResponse: PlaceResponse
            get() = PlaceResponse(idPlace, "Jerez", emptyList(), "amazing place", null, false)

        val placesResponse: List<PlaceResponse>
            get() = listOf(
                PlaceResponse(1, "Jerez", emptyList(), "Amazing place little town", null, false),
                PlaceResponse(2, "Fresnillo", emptyList(), "Cool place", null, false),
                PlaceResponse(3, "Zacatecas Capital", emptyList(), "Incredible place", null, false),
                PlaceResponse(4, "Malpazo", emptyList(), "Incredible tortas ", null, false),
                PlaceResponse(5, "Susticacan", emptyList(), "Beautiful place to stay here", null, false),
                PlaceResponse(6, "Florencia de Benito Juarez", emptyList(), "Relaxed place to visit", null, false)
            )

        val idState: Int
            get() = 1

        val state: StateResponse
            get() = StateResponse(idState, "Zacatecas", placesResponse)

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