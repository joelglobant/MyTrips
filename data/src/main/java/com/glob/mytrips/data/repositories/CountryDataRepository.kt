package com.glob.mytrips.data.repositories

import com.glob.mytrips.data.data.CountryDataStoreFactory
import com.glob.mytrips.data.mappers.datatodto.CountryDataToDtoMapper
import com.glob.mytrips.domain.dtos.CountryDto
import com.glob.mytrips.domain.dtos.StateDto
import com.glob.mytrips.domain.repositories.CountryRepository
import io.reactivex.Completable
import io.reactivex.Single

class CountryDataRepository(
    private val factory: CountryDataStoreFactory//,
    //private val countryDataToDto: CountryDataToDtoMapper
) : CountryRepository {

    override fun getCountry(idUser: Int): Single<List<CountryDto>> {
        return factory.retrieveLocalDataSource().isCached(idUser)
            .flatMap {
                factory.retrieveDataSource(it).getCountries(idUser)
            }.flatMap { countries ->
                countries.forEach { country ->
                    country.let {
                        //savePlaces(it)
                    }
                }
                return@flatMap saveCountries(countries).toSingle { countries }
            }
    }

    override fun saveCountries(countries: List<CountryDto>): Completable {
        return factory.retrieveLocalDataSource().saveCountries(countries)
    }

    private fun savePlaces(places: List<StateDto>) {
        //TODO() will be continued in the next sprint!
    }

}