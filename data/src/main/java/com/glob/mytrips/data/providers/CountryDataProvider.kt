package com.glob.mytrips.data.providers

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.providers.CountryProvider
import com.glob.mytrips.domain.repositories.CountryRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import com.glob.mytrips.domain.usecases.country.GetCountryUseCase

class CountryDataProvider(
    private val countryRepository: CountryRepository,
    private val threadExecutor: ThreadExecutor,
    private val postExecutorThread: PostExecutorThread
) : CountryProvider {

    override fun getCountryUseCase(idUser: Int): SingleUseCase<GetCountryUseCase.Params, List<CountryDto>> {
        return GetCountryUseCase(countryRepository, threadExecutor, postExecutorThread)
    }
}