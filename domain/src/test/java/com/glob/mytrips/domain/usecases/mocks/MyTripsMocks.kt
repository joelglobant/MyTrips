package com.glob.mytrips.domain.usecases.mocks

import com.glob.mytrips.domain.dtos.*

class MyTripsMocks {

    val countryMock: CountryDto
        get() = CountryDto(
            1,
            "Mexico",
            listOf(stateMock)
        )

    val stateMock: StateDto
        get() = StateDto(
            1,
            "Zacatecas",
            listOf(placeMock)
        )

    val placeMock: PlaceDto
        get() = PlaceDto(
            1,
            "Jerez",
            listOf(PhotoDto("www.photo.com")),
            "A magic twon",
            4.5,
            true
        )

    val userMock: UserDto
        get() = UserDto(
            1,
            "joel",
            "Leoboyean",
            "Aparicio P",
            "Engineer|traveler",
            listOf(countryMock)
        )

}