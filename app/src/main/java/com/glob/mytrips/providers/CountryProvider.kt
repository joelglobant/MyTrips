package com.glob.mytrips.providers

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.country.GetCountryUseCase

interface CountryProvider {
    fun getCountryUseCase(): SingleUseCase<GetCountryUseCase.Params, List<CountryDto>>
}