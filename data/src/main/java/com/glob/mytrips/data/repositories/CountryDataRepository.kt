package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.CountryDataStoreFactory
import com.glob.mytrips.data.mappers.datatodto.CountryDataToDtoMapper
import com.glob.mytrips.data.mappers.datatodto.StateDataToDtoMapper
import com.glob.mytrips.data.model.StateData
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.repositories.CountryRepository
import com.glob.mytrips.domain.repositories.StateRepository
import io.reactivex.Completable
import io.reactivex.Single

class CountryDataRepository(
    private val factory: CountryDataStoreFactory,
    private val stateRepository: StateRepository,
    private val countryDataToDto: CountryDataToDtoMapper,
    private val stateDataToDto: StateDataToDtoMapper
) : CountryRepository {

    override fun getCountries(idUser: Int): Single<List<CountryDto>> {
        return factory.retrieveLocalDataSource().isCached(idUser)
            .flatMap {
                factory.retrieveDataSource(it).getCountries(idUser)
            }.flatMap { countries ->
                countries.forEach { country ->
                    saveStates(country.states)
                }
                return@flatMap saveCountries(
                    countries.map {
                        countryDataToDto.transform(it)
                    }
                ).toSingle {
                    countries.map {
                        countryDataToDto.transform(it)
                    }
                }
            }
    }

    override fun saveCountries(countries: List<CountryDto>): Completable {
        return factory.retrieveLocalDataSource().saveCountries(countries)
    }

    private fun saveStates(states: List<StateData>?) {
        states?.let { statesData ->
            stateRepository.saveStates(statesData.map { stateDataToDto.transform(it) })
        }
    }

}