package com.glob.mytrips.data.remote

import com.glob.mytrips.data.mappers.responsetodata.CountryResponseToDataMapper
import com.glob.mytrips.data.model.CountryData
import com.glob.mytrips.data.repositories.datastore.CountryDataStore
import com.glob.mytrips.data.repositories.datastore.remote.CountryRemote
import com.glob.mytrips.domain.dtos.CountryDto
import io.reactivex.Completable
import io.reactivex.Single

class CountryRemoteDataStore(
    private val countryRemote: CountryRemote,
    private val countryRespToData: CountryResponseToDataMapper
) : CountryDataStore {
    override fun getCountries(idUser: Int): Single<List<CountryData>> {
        return countryRemote.getCountries(idUser)
            .map { countriesResp ->
                countriesResp.map { countryRespToData.transform(it) }
            }
    }

    override fun saveCountry(country: CountryDto): Completable {
        throw UnsupportedOperationException()
    }

    override fun saveCountries(countries: List<CountryDto>): Completable {
        throw UnsupportedOperationException()
    }

    override fun isCached(idUser: Int): Single<Boolean> {
        throw UnsupportedOperationException()
    }
}