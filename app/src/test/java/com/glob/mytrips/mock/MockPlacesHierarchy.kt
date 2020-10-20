package com.glob.mytrips.data.mocks

import com.glob.mytrips.models.CountryModel
import com.glob.mytrips.models.PlaceModel
import com.glob.mytrips.models.StateModel
import com.glob.mytrips.models.UserModel

class MockPlaces {
    companion object {
        val idPlace: Int
            get() = 1

        private val place: PlaceModel
            get() = PlaceModel(idPlace, "Jerez", emptyList(), "amazing place", null, false)

        val places: List<PlaceModel>
            get() = listOf(
                PlaceModel(1, "Jerez", emptyList(), "Amazing place little town", null, false),
                PlaceModel(2, "Fresnillo", emptyList(), "Cool place", null, false),
                PlaceModel(3, "Zacatecas Capital", emptyList(), "Incredible place", null, false),
                PlaceModel(4, "Malpazo", emptyList(), "Incredible tortas ", null, false),
                PlaceModel(5, "Susticacan", emptyList(), "Beautiful place to stay here", null, false),
                PlaceModel(6, "Florencia de Benito Juarez", emptyList(), "Relaxed place to visit", null, false)
            )

        val idState: Int
            get() = 1

        val state: StateModel
            get() = StateModel(idState, "Zacatecas", places)

        val idCountry: Int
            get() = 1

        val country: CountryModel
            get() = CountryModel(idCountry, "Mexico", listOf(state))

        val idUser: Int
            get() = 1

        val userResponse: UserModel
            get() = UserModel(
                idUser,
                "joel",
                "Leoboyean",
                "Aparicio P",
                "traveler",
                listOf(country)
            )

    }
}