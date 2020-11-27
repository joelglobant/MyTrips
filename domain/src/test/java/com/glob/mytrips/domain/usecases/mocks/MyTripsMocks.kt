package com.glob.mytrips.domain.usecases.mocks

import com.glob.mytrips.domain.dtos.*

object MyTripsMocks {

    val photoMock: PhotoDto
        get() = PhotoDto(
            1,
            1,
            "https://mms.businesswire.com/media/20200501005417/en/788880/5/IMGLOGO_Primary_CMYK_Blue_Rel_highres.jpg?download=1"
        )

    val placeMock: PlaceDto
        get() = PlaceDto(
            1,
            1,
            "Jerez",
            "A magic twon",
            4.5,
            true
        )

    val stateMock: StateDto
        get() = StateDto(
            1,
            1,
            "Zacatecas"
        )

    val countryMock: CountryDto
        get() = CountryDto(
            1,
            1,
            "Mexico"
        )


    val userMock: UserDto
        get() = UserDto(
            1,
            "joel",
            "Leoboyean",
            "Aparicio P",
            "Engineer|traveler"
        )

}