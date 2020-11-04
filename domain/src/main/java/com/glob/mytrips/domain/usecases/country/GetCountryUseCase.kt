package com.glob.mytrips.domain.usecases.country

import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.executors.PostExecutorThread
import com.glob.mytrips.domain.executors.ThreadExecutor
import com.glob.mytrips.domain.repositories.CountryRepository
import com.glob.mytrips.domain.usecases.SingleUseCase
import io.reactivex.Single

class GetCountryUseCase(
    private val countryRepository: CountryRepository,
    threadExecutor: ThreadExecutor,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetCountryUseCase.Params, List<CountryDto>> (
    threadExecutor,
    postExecutorThread
) {
    data class Params(val idUser: Int)

    override fun buildSingleUseCase(params: Params?): Single<List<CountryDto>> {
        return params?.let {
            countryRepository.getCountries(it.idUser)
        } ?: Single.error(Throwable("Photos not found"))
    }
}