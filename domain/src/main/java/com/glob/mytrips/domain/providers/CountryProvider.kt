package com.glob.mytrips.domain.providers

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.country.GetCountryUseCase

interface CountryProvider {
    fun getCountryUseCase(idUser: Int): SingleUseCase<GetCountryUseCase.Params, CountryDto>
}