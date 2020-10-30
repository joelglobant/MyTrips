package com.glob.mytrips.data.providers

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.providers.CountryProvider
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.country.GetCountryUseCase

class CountryDataProvider(
    //private val

) : CountryProvider {

    override fun getCountryUseCase(idUser: Int): SingleUseCase<GetCountryUseCase.Params, CountryDto> {
        TODO() ///return  //GetCountryUseCase()
    }
}