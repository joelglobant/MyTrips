package com.glob.mytrips.data.mocks

import com.glob.mytrips.data.remote.response.CountryResponse
import com.glob.mytrips.data.remote.response.PlaceResponse
import com.glob.mytrips.data.remote.response.StateResponse
import com.glob.mytrips.data.remote.response.UserResponse
import com.glob.mytrips.domain.dtos.PlaceDto

class MockPlaces {
    companion object {
        val idPlace: Int
            get() = 1

        private val placeResponse: PlaceResponse
            get() = PlaceResponse(idPlace, "Jerez", emptyList(), "amazing place", null, false)

        fun places(): List<PlaceDto> {
            return listOf(
                PlaceDto(1, "Jerez", emptyList(), "Amazing place little town", null, false),
                PlaceDto(2, "Fresnillo", emptyList(), "Cool place", null, false),
                PlaceDto(3, "Zacatecas Capital", emptyList(), "Incredible place", null, false),
                PlaceDto(4, "Florida", emptyList(), "Incredible tortas ", null, false),
                PlaceDto(5, "Susticacan", emptyList(), "Beautiful place to stay here", null, false),
                PlaceDto(6, "Florencia de Benito Juarez", emptyList(), "Relaxed place to visit", null, false),
                PlaceDto(7, "Teúl", emptyList(), "Relaxed place to visit", null, false),
                PlaceDto(8, "Monte Escobedo", emptyList(), "Relaxed place to visit", null, false),
                PlaceDto(9, "Nochistlan", emptyList(), "Relaxed place to visit", null, false),
                PlaceDto(10, "Loreto", emptyList(), "Relaxed place to visit", null, false)
            )
        }

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